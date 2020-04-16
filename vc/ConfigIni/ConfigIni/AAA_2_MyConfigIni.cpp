#include "TRegConfigIni.h"
#define __CONFIG_INI_CPP__
#define ConfigIni(type, name, value)  extern const type  name;const type  name = value;static TRegConfigIni name##reg((type*)(&name),#name,__FILE__,__LINE__);
#include "TMyConfigIni.h"

static TRegConfigIniLoadFromFile RegConfigIniLoadFromFile;