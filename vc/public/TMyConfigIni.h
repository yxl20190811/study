#pragma once

#include "TConfigIni.h"
#include <string>

//�������Щ���ò����Ǹ���Ԫ����ʹ�ã������޸�
ConfigIni(__int64, UTTestParam1, 1);
ConfigIni(int, UTTestParam2, 2);
ConfigIni(std::string, UTTestParam3, "3");
ConfigIni(double, UTTestParam4, 4.0);

//�����������ò���
ConfigIni(__int64, LogFileLevel, 1);
ConfigIni(__int64, LogScreenLevel, 1);
