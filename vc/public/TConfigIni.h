#pragma once

#ifndef __CONFIG_INI_CPP__
#define ConfigIni(type, name, value)  class name{public:static type get(){return m_value;};private:static type m_value;};
#endif