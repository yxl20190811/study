#pragma once

#include "TConfigIni.h"
#include <string>

ConfigIni(__int64, LogFileLevel, 1);
ConfigIni(__int64, LogScreenLevel, 1);
ConfigIni(int, ServerPort, 10);
ConfigIni(std::string, ServerAddress, "192.168.1.1");
ConfigIni(double, ServerPrice, 12.5);