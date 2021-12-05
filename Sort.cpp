#include "Sort.h"

bool cmp(const string& a,const string& b){
    return a<b;
}

bool Sort::exec(vector<string> *input, vector<string> *output) {
    std::sort(input->begin(), input->end(), cmp);
}