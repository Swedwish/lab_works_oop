#include <iostream>
#include "ReadFile.cpp"
#include "WriteFile.cpp"
#include "Sort.cpp"
#include "Grep.cpp"
#include "Replace.cpp"
#include "Dump.cpp"
#include "unordered_map"

int arg_count(const string& funcName){
    if (funcName == "sort"){
        return 0;
    }
    else if (funcName == "dump" || funcName == "readfile" || funcName == "writefile" || funcName == "Grep"){
        return 1;
    }
    else{
        return 2;
    }
}

void execute_block(int id,vector<string>* input, vector<string>* output, unordered_map<int,vector<string>>& commands){
    vector<string> args = commands[id];
    Worker* prod;
    if (args[0] == "readfile"){
        ReadFile temp(args[1]);
        prod = &temp;
    }
    else if (args[0] == "writefile"){
        WriteFile temp(args[1]);
        prod = &temp;
    }
    else if (args[0] == "grep"){
        Grep temp(args[1]);
        prod = &temp;
    }
    else if (args[0] == "sort"){
        Sort temp;
        prod = &temp;
    }
    else if (args[0] == "replace"){
        Replace temp(args[1],args[2]);
        prod = &temp;
    }
    else if (args[0] == "dump"){
        Dump temp(args[1]);
        prod = &temp;
    }
    else{
        exit(0);
        //TODO:err here
    }
    input = output;
    prod->exec(input,output);
}

int main() {
    unordered_map<int,vector<string>> commands;
    ifstream stream("workflow.txt");
    string str;
    vector<string> func_data;
    stream>>str;
    if (str != "desc"){
        exit(0);
    }
    int iteration = 1;
    int index;
    while (true){
        if (iteration == 1) {
            stream >> str;
            if (str == "csed") {
                break;
            }
            index = stoi(str);
            iteration++;
        }
        else if (iteration == 2) {
            stream >> str;
            if (str != "=") {
                exit(0);
            }
            iteration++;
        }
        else if (iteration == 3) {
            stream >> str;
            func_data.push_back(str);
            for (int i = 0; i < arg_count(str); i++) {
                stream >> str;
                func_data.push_back(str);
            }
            commands[index] = func_data;
            func_data.clear();
            iteration = 1;
        }
    }

    int id;
    vector<string> input1, output1;
    vector<string> *input = &input1, *output = &output1;
    while (stream>>str){
        if (str == "->") continue;
        id = stoi(str);
        execute_block(id,input,output,commands);
    }


    return 0;
}
