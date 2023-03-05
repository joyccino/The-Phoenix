using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OAExperimentSchedule.Properties
{
    internal class calculator_230227_joyccino
    {
        public static double Calculator(double num1, double num2, char op)
        {
            switch (op)
            {
                case '+':
                    return num1 + num2;
                case '-':
                    return num1 - num2;
                case 'x':
                    return num1 * num2;
                case '÷':
                    if (num2 != 0)
                    {
                        return num1 / num2;
                    }
                    else
                    {
                        throw new DivideByZeroException("0으로 나눌 수 없습니다.");
                    }
                default:
                    throw new ArgumentException("지원하지 않는 연산자입니다.");
            }
        }
    }
}
