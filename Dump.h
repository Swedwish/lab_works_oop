#ifndef LAB2_DUMP_H
#define LAB2_DUMP_H
#include "Worker.h"

class Dump: public Worker {
public:
    vector <string> args;
    Dump(string filename);
    bool exec(vector<string> *input, vector<string> *output) override;
};


#endif //LAB2_DUMP_H
