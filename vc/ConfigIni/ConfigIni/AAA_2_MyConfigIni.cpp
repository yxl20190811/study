#include "TRegConfigIni.h"
#define __CONFIG_INI_CPP__
#define ConfigIni(type, ClassName, value)  class ClassName{public:static type get(){return m_value;};private:static type m_value;friend type* __Get##ClassName##StaticValue();};type ClassName::m_value = value;type* __Get##ClassName##StaticValue(){return &(ClassName::m_value);};static TRegConfigIni ClassName##reg(__Get##ClassName##StaticValue(), #ClassName,__FILE__,__LINE__);


#include "TMyConfigIni.h"

static TRegConfigIniLoadFromFile RegConfigIniLoadFromFile;