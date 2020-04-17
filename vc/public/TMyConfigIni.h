#pragma once

#include "TConfigIni.h"
#include <string>

//下面的这些配置参数是给单元测试使用，请勿修改
ConfigIni(__int64, UTTestParam1, 1);
ConfigIni(int, UTTestParam2, 2);
ConfigIni(std::string, UTTestParam3, "3");
ConfigIni(double, UTTestParam4, 4.0);

//请添加你的配置参数
ConfigIni(__int64, LogFileLevel, 1);
ConfigIni(__int64, LogScreenLevel, 1);
