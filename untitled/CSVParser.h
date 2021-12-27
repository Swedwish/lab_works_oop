#pragma once

#include "Iterator.h"
#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <tuple>

template<class ...Types>
class CSVParser {
    std::ifstream *file;

public:
    int skip_lines;

    CSVParser(std::ifstream &stream, int skip) : file(&stream), skip_lines(skip) {};

    Iterator<Types...> begin() {
        return Iterator<Types...>(file, skip_lines, false);
    }

    Iterator<Types...> end() {
        return Iterator<Types...>(file, skip_lines, true);
    }

};
