// You can edit this code!
// Click here and start typing.
package main

import "fmt"

func main() {
	result := 0
	var err error = nil

	result, err = Calculator(9, 3, 'x')

	if err == nil {
		fmt.Println(result)
	} else {
		fmt.Println(err)
	}

}

func Calculator(num1 int, num2 int, operator rune) (int, error) {

	switch operator {
	case '+':
		result := 0
		result = num1 + num2
		return result, nil
	case '-':
		result := 0
		result = num1 - num2
		return result, nil
	case 'x':
		result := 0
		result = num1 * num2
		return result, nil
	case '÷':
		if num2 != 0 {
			result := 0
			result = num1 / num2

			return result, nil
		} else {
			return 0, fmt.Errorf("0으로 나눌 수 없습니다.")
		}
	default:
		return 0, fmt.Errorf("지원하지 않는 연산자입니다.")
	}
}
