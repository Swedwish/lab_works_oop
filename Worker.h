#ifndef LAB2_WORKER_H
#define LAB2_WORKER_H
#include <string>
#include <vector>
#pragma once

using namespace std;

class Worker {
protected:
    vector <string> args;
public:
    virtual ~Worker() = default;
    virtual bool exec(vector <string> *input, vector <string> * output) = 0;
};


#endif //LAB2_WORKER_H
