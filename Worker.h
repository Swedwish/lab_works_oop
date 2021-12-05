#ifndef LAB2_WORKER_H
#define LAB2_WORKER_H
#include <string>
#include <vector>

using namespace std;

class Worker {
protected:
    vector <string> args;
public:
    virtual ~Worker() = default;
    virtual bool exec(vector <string> *input, vector <string> * output, bool haveIn, bool haveOut) = 0;
};


#endif //LAB2_WORKER_H
