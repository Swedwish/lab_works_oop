#ifndef LAB2_WRITEFILE_H
#define LAB2_WRITEFILE_H
#include "Worker.h"

class WriteFile: public Worker{
public:
    WriteFile(string out);
    bool exec(vector<string> *input, vector<string> *output) override;
};


#endif //LAB2_WRITEFILE_H
