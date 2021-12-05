#include "grep.h"

grep::grep(string word) {
    args.push_back(word);
}

bool grep::exec(vector<string> *input, vector<string> *output) {
    for (auto &i : *input){
        if(i.find(args[0])!=string::npos){
            output->push_back(i);
        }
    }
    return true;
}

