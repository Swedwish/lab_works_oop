#ifndef LAB2_REPLACE_H
#define LAB2_REPLACE_H
#include "Worker.h"

class Replace:public Worker {
public:
    Replace(const string& word1, const string& word2);
    bool exec(vector<string> *input, vector<string> *output) override;
};


#endif //LAB2_REPLACE_H
