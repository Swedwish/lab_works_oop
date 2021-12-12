#ifndef LAB2_VALIDATOR_H
#define LAB2_VALIDATOR_H
#pragma once
#include <string>
#include <sys/stat.h>
#include <stdexcept>
using namespace std;

class Validator
{
public:
    static bool file_Exists(const string& file);
    static void badFile(const string& message);
};

#endif //LAB2_VALIDATOR_H
