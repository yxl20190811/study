#pragma once
#include <string>

class TRegUt
{
public:
  int (*m_fun)();
  std::string m_name;
  std::string m_FileName;
  int m_FileLine;
public:
  TRegUt(int (*fun)(), const char* name, const char* FileName, int FileLine);
};

#define UT_TEST(name)  int UT_##name(); static TRegUt __UT_##name##__UTReg__(UT_##name, #name, __FILE__, __LINE__); int UT_##name()

extern void ut_test();