#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <ctype.h>
#include <thread>
#include <string.h>

#define BUFFER_SIZE 1024

void ThreadFun(int* pid, bool* isExit) {
    waitpid(*pid, NULL, 0);
    *isExit = true;
}

int main() {
    //printf("\r\n main process start");
    int fd1[2], fd2[2];
    pid_t pid1, pid2, pid3;
    char buffer[BUFFER_SIZE];


    if (pipe(fd1) == -1) {
        perror("pipe");
        exit(EXIT_FAILURE);
    }
    if (pipe(fd2) == -1) {
        perror("pipe2");
        exit(EXIT_FAILURE);
    }

    pid1 = fork();

    if (pid1 == -1) {
        perror("fork1");
        exit(EXIT_FAILURE);
    }
    else if (pid1 == 0) {
        // child process 1 reads the text file line by line and writes to pipe 1
        close(fd1[0]);
        FILE* fp;
        char line[BUFFER_SIZE];
        fp = fopen("/1.txt", "r");
        if (fp == NULL) {
            printf("Input File not found!\n");
            exit(EXIT_FAILURE);
        }
        int len = 0;
        while (fgets(line, BUFFER_SIZE, fp) != NULL) {
            len += strlen(line);
            write(fd1[1], line, len);
        }
        fclose(fp);
        close(fd1[1]);
        printf("\r\n child process 1  read ch %d", len);
        exit(EXIT_SUCCESS);
    }
    else {
        pid2 = fork();

        if (pid2 == -1) {
            perror("fork2");
            exit(EXIT_FAILURE);
        }
        else if (pid2 == 0) {
            // child process 2 reads from pipe 1, reverses capitalization, and writes to pipe 2
            fd_set set;
            struct timeval timeout;
            FD_ZERO(&set); /* clear the set */
            FD_SET(fd1[0], &set); /* add our file descriptor to the set */
            timeout.tv_sec = 0;
            timeout.tv_usec = 100;
            ////////////////////////////////////////////////////////////////////////////////////
            close(fd1[1]);
            close(fd2[0]);
            int n;
            char c;
            bool IsExit = false;
            std::thread newThread(ThreadFun, &pid1, &IsExit);
            int len = 0;
            while (true) {
                int rv = select(fd1[0] + 1, &set, NULL, NULL, &timeout);
                if (rv == -1) {
                    perror("select"); /* an error accured */
                    continue;
                }
                if (0 == rv) {//超时
                    if (IsExit) {
                        break;
                    }
                    continue;
                }
                n = read(fd1[0], &c, 1);
                if (n <= 0) {
                    continue;
                }
                ++len;
                //printf("\r\nread a line from pipe");
                if (islower(c)) {
                    c = toupper(c);
                }
                else if (isupper(c)) {
                    c = tolower(c);
                }
                write(fd2[1], &c, n);
            }
            close(fd1[0]);
            close(fd2[1]);
            printf("\r\n child process 2 read: %d", len);
            exit(EXIT_SUCCESS);
        }
        else {
            pid3 = fork();

            if (pid3 == -1) {
                perror("fork3");
                exit(EXIT_FAILURE);
            }
            else if (pid3 == 0) {
                // child process 3 reads from pipe 2 and writes to output.txt
                close(fd2[1]);
                FILE* fp;
                fp = fopen("/output.txt", "w");
                if (fp == NULL) {
                    printf("File not found!\n");
                    exit(EXIT_FAILURE);
                }

                fd_set set;
                struct timeval timeout;
                FD_ZERO(&set); /* clear the set */
                FD_SET(fd2[0], &set); /* add our file descriptor to the set */
                timeout.tv_sec = 0;
                timeout.tv_usec = 1;

                int n;
                bool IsExit = false;
                std::thread newThread(ThreadFun, &pid2, &IsExit);
                int len = 0;
                while (true) {
                    int rv = select(fd2[0] + 1, &set, NULL, NULL, &timeout);
                    if (rv == -1) {
                        perror("select"); /* an error accured */
                        continue;
                    }
                    if (0 == rv) {//超时
                        if (IsExit) {
                            break;
                        }
                        continue;
                    }
                    n = read(fd2[0], buffer, BUFFER_SIZE);
                    len += n;
                    //printf("\r\nread a c from pipe");
                    fwrite(buffer, sizeof(char), n, fp);
                }
                fclose(fp);
                close(fd2[0]);
                printf("\r\n child process 3 read: %d", len);
                exit(EXIT_SUCCESS);
            }
            else {
                // parent process waits for all child processes to finish
                close(fd1[0]);
                close(fd1[1]);
                close(fd2[0]);
                close(fd2[1]);
                waitpid(pid1, NULL, 0);
                printf("\r\nchild process 1 exit");
                waitpid(pid2, NULL, 0);
                printf("\r\nchild process 2 exit");
                waitpid(pid3, NULL, 0);
                printf("\r\nchild process 3 exit");
            }
        }
    }
    return 0;
}

