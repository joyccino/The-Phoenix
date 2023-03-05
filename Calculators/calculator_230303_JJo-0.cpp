#include <iostream>
#include <string>

using namespace std;

//모듈 형태의 함수
//memory 관리 위해서 calculator 함수 외에는 다 인수
void input(int *a, int *b, char *c)
{
    char str[CHAR_MAX];
    //계속해서 입력 받기
    while(1)
    {
        cout << "Enter 1 arithmetic operator(end char '_') : ";
        cin >> str;
        *c = str[0];
        //1개의 글자만 들어올 경우 && 특수문자 경우 에만
        if(strlen(str) == 1 && ((*c == '+') || (*c == '-') || (*c == '/') || (*c == '*')))
        {
            cin.clear();
            cin.ignore(INT_MAX,'\n');
            break;
        }
        else if(*c == '_') return;
        else 
        {
            //에러의 경우 들어온 버퍼나 다른 값 버퍼 지우기
            cout << "ERROR please enter arithmetic operator" << endl;
            cin.clear();
            //압력버퍼의 모든내용 추출해서 버림
            cin.ignore(INT_MAX,'\n');
        }
    }
    while(1)
    {
        cout << "Enter 2 integers with space : ";
        cin >> *a >> *b;
        if(!cin)
        {
            cout << "ERROR please enter integers" << endl;
            cin.clear();
            cin.ignore(INT_MAX,'\n');
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
    cout << *a << " - "<< *b << " = " << (*a - *b) << endl;
    //인수 초기화
    *a = 0; *b = 0; *c = ' ';
}
void multiply(int *a, int *b, char *c)
{
    cout << *a << " * "<< *b << " = " << (*a * *b) << endl;
    //인수 초기화
    *a = 0; *b = 0; *c = ' ';
}
void divide(int *a, int *b, char *c)
{
    if(*a % *b == 0)
    {
        cout<< *a << " / "<< *b << " = " << (*a / *b) << endl;
    }
    else
    {
        cout<< *a << " / "<< *b << " = " << (*a / *b) << "\n나머지 : " << (*a % *b) << endl;
    }
    //인수 초기화
    *a = 0; *b = 0; *c = ' ';

}

//함수 부분
int calculator(int x, int y, char z)
{
    //인수 선언, 관련 함수 쓰기 위해 포인터 설정
    char *c = &z; 
    int *a = &x; 
    int *b = &y;
    while(1)
    {
        input(a,b,c);
        switch(z)
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