#ifndef LAB1_HEADER_H
#define LAB1_HEADER_H

#include <vector>
#include <bitset>
#include <malloc.h>
typedef unsigned int uint;

enum Trit
{
    Unknown,
    True,
    False
};

using namespace std;

int bit(uint b, uint bitNumber){
    return (b & (1 << bitNumber))>>bitNumber;
}

int gtrit(uint b, uint bitNumber){
    if (bit(b, bitNumber) == 1){
        return True;
    }
    else if (bit(b, bitNumber + 1) == 1)
    {
        return False;
    }
    else
    {
        return Unknown;
    }
}

class TritSet{
public:
    uint length;
    uint* data;
    class reference{
    public:
        uint index;
        uint value[1];
        TritSet* last;
        reference(){
            index = 0;
            value[0] = 0;
        }
        void operator=(uint value){
            uint bit_index = index*2/(8*sizeof(uint));

            if (value == False){
                last->data[bit_index] |= 1 << (index%16*2 + 1);//second bit index
            }
            else{
                last->data[bit_index] &= ~(1 << (index%16*2 + 1));//second bit index
            }
            if (value == True){
                last->data[bit_index] |= 1 << (index%16*2); //first bit index
            }
            else{
                last->data[bit_index] &= ~(1 << (index%16*2)); //first bit index
            }
        }
    };

    uint capacity(){
        return length;
    }

    unsigned int cardinality(Trit value){

    };

    explicit TritSet(uint len){
        length = len;
        data = new uint[len*2/(8*sizeof (uint))];
        //data = (uint*) malloc(len*2/(8*sizeof (uint)));
        for (uint i = 0; i < (len*2/(8*sizeof (uint))); i++){
            data[i] = 0;
        }
    }

    TritSet(){
        length = 0;
        data = nullptr;
    }

    reference kekw;

    reference& operator[](uint index){
        kekw.last = this;
        kekw.index=index;
        return kekw;
    }

    void operator=(TritSet Trit_ban){
        delete [] this->data;
        data = new uint[Trit_ban.length*2/(8*sizeof (uint))];
        for (uint i = 0; i < (Trit_ban.length*2/(8*sizeof (uint))); i++){
            this->data[i] = Trit_ban.data[i];
        }
        this->length = Trit_ban.length;
    }

    TritSet operator& (TritSet second){
        uint maxlength;
        if (this->length > second.length){
            maxlength = this->length;
        }
        else{
            maxlength = second.length;
        }
        TritSet temp(maxlength);

        for (uint i = 0; i < (maxlength); i++){
            uint byte_index = ((i * 2) / (8 * sizeof(uint)));
            uint bit_index = ((i * 2) % (8 * sizeof(uint)));

            int ftrit = gtrit(this->data[byte_index], bit_index);
            int strit = gtrit(second.data[byte_index], bit_index);

            if ((ftrit == False) || (strit == False)){
                temp[i] = False;
            }
            else if (ftrit == Unknown || strit == Unknown){
                temp[i] = Unknown;
            }
            else{
                temp[i] = True;
            }
        }
        return temp;
    }

    TritSet operator| (TritSet second){
        uint maxlength;
        if (this->length > second.length){
            maxlength = this->length;
        }
        else{
            maxlength = second.length;
        }
        TritSet temp(maxlength);

        for (uint i = 0; i < (maxlength); i++){
            uint byte_index = ((i * 2) / (8 * sizeof(uint)));
            uint bit_index = ((i * 2) % (8 * sizeof(uint)));

            int ftrit = gtrit(this->data[byte_index], bit_index);
            int strit = gtrit(second.data[byte_index], bit_index);

            if ((ftrit == True) || (strit == True)){
                temp[i] = True;
            }
            else if (ftrit == Unknown || strit == Unknown){
                temp[i] = Unknown;
            }
            else{
                temp[i] = False;
            }
        }
        return temp;
    }

    TritSet operator! (void){
        TritSet temp(this->length);
        for (uint i = 0; i < (this->length); i++){
            uint byte_index = ((i * 2) / (8 * sizeof(uint)));
            uint bit_index = ((i * 2) % (8 * sizeof(uint)));
            int ftrit = gtrit(this->data[byte_index], bit_index);
            if (ftrit == False){
                temp[i] = True;
            }
            else if (ftrit == Unknown){
                temp[i] = Unknown;
            }
            else{
                temp[i] = False;
            }
        }
        return temp;
    }
};
#endif //LAB1_HEADER_H
