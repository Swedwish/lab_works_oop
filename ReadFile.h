#ifndef LAB2_READFILE_H
#define LAB2_READFILE_H
#include "Worker.h"
#include <fstream>

class ReadFile:public Worker {
public:
    ReadFile(string in);
    bool exec(vector<string> *input, vector<string> *output) override;
};


#endif //LAB2_READFILE_H
