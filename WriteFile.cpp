#include "WriteFile.h"

bool WriteFile::exec(vector<string> *input, vector<string> *output) {
    ofstream stream(args[0]);
    for (auto &i : *input){
        stream<<i<<endl;
    }
}

WriteFile::WriteFile(string out) {
    args.push_back(out);
}
