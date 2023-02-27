void main() {
  double result = calculator(8, 9, 'x');
  print(result);
}

double calculator(double num1, double num2, String operator) {
  switch (operator) {
    case '+':
      return num1 + num2;
    case '-':
      return num1 - num2;
    case 'x':
      return num1 * num2;
    case '÷':
      if (num2 != 0) {
        return num1 / num2;
      } else {
        throw Exception("0으로 나눌 수 없습니다.");
      }
    default:
      throw Exception("지원하지 않는 연산자입니다.");
  }
}