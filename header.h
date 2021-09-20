#ifndef UNTITLED1_HEADER_H
#define UNTITLED1_HEADER_H
#include <bitset>
#include <malloc.h>
enum Trit{Unknown,True, False};
using namespace std;

class TritSet{
public:
    int length;
    bitset<2>* data;

    TritSet(){
        length = 0;
        data = nullptr;
    }
    TritSet(const int len){
        length = len;
        data = (bitset<2>*) malloc(len*2/sizeof(bitset<2>));
    }

    bitset<2>& operator[] (const int index){
        return data[index];
    }
    TritSet* operator& (TritSet* that){
        int maxx = max(this->length,that->length);
        TritSet newone;
        newone.data = (bitset<2>*)malloc(maxx/4+1);
        for (int i = 0; i < maxx; i++){
            if (this->data->to_ulong() == False || that->data->to_ulong() == False){
                newone.data[i] = False;
            }
            else if (this->data->to_ulong() == Unknown || that->data->to_ulong() == Unknown){
                newone.data[i] = Unknown;
            }
            else{
                newone.data[i] = True;
            }
        }
    }
};


#endif //UNTITLED1_HEADER_H
