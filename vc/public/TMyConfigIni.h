#pragma once

#include "TConfigIni.h"
#include <string>

//�������Щ���ò����Ǹ���Ԫ����ʹ�ã������޸�
ConfigIni(__int64, UTTestParam1, 1);
ConfigIni(int, UTTestParam2, 2);
ConfigIni(std::string, UTTestParam3, "3");
ConfigIni(double, UTTestParam4, 4.0);

//�����������ò���
ConfigIni(int, LogFileLevel, 1);
ConfigIni(int, LogScreenLevel, 1);
ConfigIni(int, DebugFileLevel, 1);

ConfigIni(int, IsUtTest, 1);