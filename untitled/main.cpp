#pragma once
#include <tuple>
#include <functional>
#include <iostream>
#include <iosfwd>
#include <vector>
#include <fstream>

using namespace std;
#include "print_tuple.h"
#include "CSVparser.h"

int main(){
    std::ifstream f("test.csv");
    CSVParser::CSVParser<std::string, int, float> parser(f, 0);
    std::cout << "Started reading" << std::endl;
    try {
        for (const auto& entry: parser) {
            cout << entry << std::endl;
        }
    } catch (CSVParser::ParsingException& e) {
        std::cout << e.what() << "; line: " << e.get_line_idx() << " char: " << e.get_char_idx() << std::endl;
    }
    std::cout << "Read all" << std::endl;
    return 0;
}