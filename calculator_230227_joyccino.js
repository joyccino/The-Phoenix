function Calculator(num1, num2, operator) {
  switch (operator) {
    case '+':
      return num1 + num2;
    case '-':
      return num1 - num2;
    case 'x':
      return num1 * num2;
    case '÷':
      return num1 / num2;
    default:
      return '지원하지 않는 연산자입니다.';
  }
}