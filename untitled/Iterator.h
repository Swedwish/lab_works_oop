
#pragma once
#include "CSVParser.h"
#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
#include <string>
#include <tuple>

template<class ...Types>
class CSVParser;

template<class ...Types>
class Iterator{
    friend class CSVParser<Types...>;
    int skip_lines;
    std::ifstream* file;
    std::stringstream* s;
    std::string str;
    std::tuple<Types...>* line;
    bool eof;
public:

    Iterator(std::ifstream* stream, int skip, bool is_eof): file(stream), skip_lines(skip), eof(is_eof){
        if(file->eof()){
            eof = true;
        }
        else {
            eof = false;
            for (int i = 0; i < skip_lines - 1; i++) {
                std::getline(*file, str, ';');
                if(file->eof()){
                    eof = true;
                    break;
                }
            }
            if(!eof) {
                std::getline(*file, str, ';');
                line = new std::tuple<Types...>;
                s->str() = str;
                *line = get_tuple<Types...>(s);
            }
        }
    }

    Iterator operator++(){
        if(file->eof()){
            eof = true;
        }
        else {
            std::getline(*file, str, ';');
            s->str() = str;
            *line = get_tuple<Types...>(s);
        }
        return *this;
    }

    bool operator==(Iterator& i){
        if(eof && i.eof){
            return true;
        }
        return (i == *this);
    }

    bool operator!=(Iterator& i){
        if(eof && i.eof){
            return false;
        }
        return (i != *this);
    }

    std::tuple<Types...>& operator*(){
        return *line;
    }

};

template<class Arg, class ...Args>
std::tuple<Arg, Args...> get_tuple(std::stringstream& s){
    Arg a;
    std::string word;
    char ch;
    do{
        s >> ch;
        word += ch;
    } while(ch != ',' && !s.eof());
    std::stringstream ss(word);
    ss >> a;
    if(sizeof...(Args) == 0){
        return std::tuple<Arg>(a);
    }
    else{
        return std::tuple_cat(std::tuple<Arg>(a), get_tuple<Args...>(s));
    }

}
