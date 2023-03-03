#include <iostream>

using namespace std;
void input(int *a, int *b, char *c)
{
    while(1)
    {
        cout << "Enter 1 arithmetic operator(end word '_') : ";
        cin >> *c;
        cin.clear();
        cin.ignore(INT_MAX,'\n');
        if( (*c == '+') || (*c == '-') || (*c == '/') || (*c == '*') ) break;
        else 
        {
            cout << "ERROR please enter arithmetic operator" << endl;
            cin.clear();
            cin.ignore(INT_MAX,'\n');
            continue;
        }
    }
    
    while(1)
    {
        cout << "Enter 2 integers with space : ";
        cin >> *a >> *b;
        cin.clear();
        cin.ignore(INT_MAX,'\n');
        if(!cin)
        {
            cout << "ERROR please enter integers" << endl;
            cin.clear();
            cin.ignore(INT_MAX,'\n');
            continue;
        }
        else break;
    }

    
}
void add(int *a, int *b, char *c)
{
    cout << *a << " + "<< *b << " = " << (*a + *b) << endl;
    //인수 초기화
    *a = 0; *b = 0; *c = ' ';
}
void subtract(int *a, int *b, char *c)
{
    cout << *a << " + "<< *b << " = " << (*a - *b) << endl;
    //인수 초기화
    *a = 0; *b = 0; *c = ' ';
}
void multiply(int *a, int *b, char *c)
{
    cout << *a << " + "<< *b << " = " << (*a * *b) << endl;
    //인수 초기화
    *a = 0; *b = 0; *c = ' ';
}
void divide(int *a, int *b, char *c)
{
    if(*a % *b == 0)
    {
        cout<< *a << " + "<< *b << " = " << (*a / *b) << endl;
    }
    else
    {
        cout<< *a << " + "<< *b << " = " << (*a / *b) << "\n나머지 : " << (*a % *b) << endl;
    }
    //인수 초기화
    *a = 0; *b = 0; *c = ' ';

}

int calculate()
{
    //인수 선언, 관련 함수 쓰기 위해 포인터 
    int in_a, in_b = 0;
    char in_c = ' ';
    char *c = &in_c; 
    int *a = &in_a; 
    int *b = &in_b;
    while(1)
    {
        input(a, b, c);
        switch(in_c)
        {
            case '+': 
                add(a,b,c);
                continue;
            case '-': 
                subtract(a,b,c);
                continue;
            case '*': 
                multiply(a,b,c);
                continue;
            case '/': 
                divide(a,b,c);
                continue;
            case '_': return 0;
        }
    }

}
