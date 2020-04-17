#include "TRegConfigIni.h"
#define __CONFIG_INI_CPP__
#define ConfigIni(type, ClassName, value)  class ClassName{public:static type get(){return m_value;};private:static type m_value;friend type* __Get##ClassName##StaticValue();};type ClassName::m_value = value;type* __Get##ClassName##StaticValue(){return &(ClassName::m_value);};static TRegConfigIni ClassName##reg(__Get##ClassName##StaticValue(), #ClassName,__FILE__,__LINE__);


#include "TMyConfigIni.h"

#include "ut.h"

static TRegConfigIniLoadFromFile RegConfigIniLoadFromFile;


UT_TEST(ConfigIni_2)
{
	if(UTTestParam1::get() != 4) return  -1;
	if(UTTestParam2::get() != 3) return  -1;
	if(UTTestParam3::get() != "2") return  -1;
	if(UTTestParam4::get() != 1.0) return  -1;

	return 1;
}

