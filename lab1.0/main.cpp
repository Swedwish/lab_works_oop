#include <iostream>
#include "header.h"
#include <bitset>

int main(void)
{
    TritSet A(5);
    TritSet B(100);
    A[1] = True;A[2] = True;A[3] = True;A[4] = True;A[5] = True;
    A[6] = True;A[7] = True;A[8] = True;A[9] = True;A[10] = True;
    B[1] = False;B[2] = False;B[3] = False;B[4] = False;B[5] = False;B[6] = False;B[7] = False;B[8] = False;B[9] = False;B[10] = False;
    TritSet C(5);
    C = A&B;
    cout<< bitset<32>(A.data[0])<<endl << A.data[0] << endl << bitset<32>(B.data[0])<<endl<< bitset<32>(C.data[0])<<endl;
    //cout<< (bitset<32>)A.data<<endl<< (bitset<32>)B.data;
    return 0;
}
//shutdown /r /t 0