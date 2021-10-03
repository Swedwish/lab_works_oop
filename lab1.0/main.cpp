#include <iostream>
#include "header.h"
#include <bitset>

int main(void)
{
    TritSet A(100);
    TritSet B(100);
    A[5] = True;
    B[6] = False;
    A[6] = True;
    A[7] = True;
    B[7] = True;
    A[8] = False;
    cout<< (bitset<32>)A.data<<endl<< (bitset<32>)B.data;
    return 0;
}
//shutdown /r /t 0