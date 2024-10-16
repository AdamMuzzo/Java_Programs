public class TestLinkedNumber {

	
	private static boolean test01 () {
		LinkedNumber ln = new LinkedNumber(1027);
		String f = traverseForward(ln.getFront());
		String b = traverseBackward(ln.getRear());
		int base = ln.getBase();
		return (f.equals("1027") && b.equals("7201") && base == 10);
	}
	
	private static boolean test02 () {
		LinkedNumber ln1 = new LinkedNumber("A7B433", 16);
		LinkedNumber ln2 = new LinkedNumber("110011001011101001", 2);
		return (ln1.toString().equals("A7B433") && ln2.toString().equals("110011001011101001"));
	}
	
	private static boolean test03 () {
		LinkedNumber ln1 = new LinkedNumber("100122011201", 2);
		LinkedNumber ln2 = new LinkedNumber("100122011201", 3);
		LinkedNumber ln3 = new LinkedNumber("BA7E2EADEE7C", 15);
		LinkedNumber ln4 = new LinkedNumber("28GA33B721DE", 16);
		return !ln1.isValidNumber() && ln2.isValidNumber() && ln3.isValidNumber() && !ln4.isValidNumber();
	}
	
	private static boolean test04 () {
		String msg = "";
		try {
			LinkedNumber ln1 = new LinkedNumber("", 2);
			ln1.getBase();
		} catch (LinkedNumberException e) {
			msg = e.getMessage();
		}
		
		boolean b1 = msg.toLowerCase().strip().equals("no digits given");
		try {
			LinkedNumber ln2 = new LinkedNumber("101210", 2);
			ln2.convert(10);
		} catch (LinkedNumberException e) {
			msg = e.getMessage();
		}
		boolean b2 = msg.toLowerCase().strip().equals("cannot convert invalid number");

		return b1 && b2;
	}
	
	private static boolean test05 () {
		LinkedNumber ln1 = new LinkedNumber(7);
		LinkedNumber ln2 = new LinkedNumber(100);
		LinkedNumber ln3 = new LinkedNumber(2459);
		boolean b1 = ln1.convert(2).toString().equals("111");
		boolean b2 = ln2.convert(2).toString().equals("1100100");
		boolean b3 = ln3.convert(2).toString().equals("100110011011");
		return b1 && b2 && b3;
	}
	
	private static boolean test06 () {
		LinkedNumber ln1 = new LinkedNumber("11101101", 2);
		LinkedNumber ln2 = new LinkedNumber("10210122", 3);
		LinkedNumber ln3 = new LinkedNumber("32133101", 4);
		boolean b1 = ln1.convert(10).toString().equals("237");
		boolean b2 = ln2.convert(10).toString().equals("2771");
		boolean b3 = ln3.convert(10).toString().equals("59345");
		return b1 && b2 && b3;
	}
	
	private static boolean test07 () {
		LinkedNumber ln1 = new LinkedNumber("11111111", 2);
		LinkedNumber ln2 = new LinkedNumber("42103204", 5);
		LinkedNumber ln3 = new LinkedNumber("13772053", 8);
		boolean b1 = ln1.convert(16).toString().equals("FF");
		boolean b2 = ln2.convert(8).toString().equals("1246250");
		boolean b3 = ln3.convert(12).toString().equals("1076837");
		return b1 && b2 && b3;
	}
	
	private static boolean test08 () {
		LinkedNumber ln1 = new LinkedNumber("110111", 2);
		LinkedNumber ln2 = new LinkedNumber("0110111", 2);
		LinkedNumber ln3 = new LinkedNumber(55);
		LinkedNumber ln4 = new LinkedNumber("110111", 2);
		LinkedNumber ln5 = new LinkedNumber("100111", 2);
		boolean b1 = !ln1.equals(ln2);
		boolean b2 = !ln1.equals(ln3);
		boolean b3 = ln1.equals(ln4);
		boolean b4 = !ln1.equals(ln5);
		return b1 && b2 && b3 && b4;
	}
	
	private static boolean test09 () {
		LinkedNumber ln = new LinkedNumber("ABCD", 16);
		ln.addDigit(new Digit('7'), 0);
		boolean b1 = ln.toString().equals("ABCD7");
		ln.addDigit(new Digit('5'), 5);
		boolean b2 = ln.toString().equals("5ABCD7");
		ln.addDigit(new Digit('9'), 3);
		boolean b3 = ln.toString().equals("5AB9CD7");
		ln.addDigit(new Digit('3'), 1);
		boolean b4 = ln.toString().equals("5AB9CD37");
		
		String f = traverseForward(ln.getFront());
		String b = traverseBackward(ln.getRear());
		return f.equals("5AB9CD37") && b.equals("73DC9BA5") && b1 && b2 && b3 && b4;
	}

	private static boolean test10 () {
		LinkedNumber ln = new LinkedNumber("32175267", 8);
		int v1 = ln.removeDigit(7);
		boolean b1 = ln.toString().equals("2175267");
		int v2 = ln.removeDigit(0);
		boolean b2 = ln.toString().equals("217526");
		int v3 = ln.removeDigit(3);
		boolean b3 = ln.toString().equals("21526");
		boolean b4 = v1 == 6291456;
		boolean b5 = v2 == 7;
		boolean b6 = v3 == 3584;

		String f = traverseForward(ln.getFront());
		String b = traverseBackward(ln.getRear());
		return f.equals("21526") && b.equals("62512") && b1 && b2 && b3 && b4 && b5 && b6;
	}
	
	
	public static void main(String[] args) {

		// getters and linked structure
		try {
			if (test01()) System.out.println("Test 1 Passed");
			else System.out.println("Test 1 Failed");
		} catch (Exception e) { System.out.println("Test 1 Failed (exception)"); }
		
		// toString
		try {
			if (test02()) System.out.println("Test 2 Passed");
			else System.out.println("Test 2 Failed");
		} catch (Exception e) { System.out.println("Test 2 Failed (exception)"); }
		
		// isValidNumber
		try {
			if (test03()) System.out.println("Test 3 Passed");
			else System.out.println("Test 3 Failed");
		} catch (Exception e) { System.out.println("Test 3 Failed (exception)"); }
		
		// exceptions
		try {
			if (test04()) System.out.println("Test 4 Passed");
			else System.out.println("Test 4 Failed");
		} catch (Exception e) { System.out.println("Test 4 Failed (exception)"); }
		
		// convert from dec
		try {
			if (test05()) System.out.println("Test 5 Passed");
			else System.out.println("Test 5 Failed");
		} catch (Exception e) { System.out.println("Test 5 Failed (exception)"); }
		
		// convert to dec
		try {
			if (test06()) System.out.println("Test 6 Passed");
			else System.out.println("Test 6 Failed");
		} catch (Exception e) { System.out.println("Test 6 Failed (exception)"); }
		
		// convert neither dec
		try {
			if (test07()) System.out.println("Test 7 Passed");
			else System.out.println("Test 7 Failed");
		} catch (Exception e) { System.out.println("Test 7 Failed (exception)"); }
		
		// equals
		try {
			if (test08()) System.out.println("Test 8 Passed");
			else System.out.println("Test 8 Failed");
		} catch (Exception e) { System.out.println("Test 8 Failed (exception)"); }
		
		// add digit
		try {
			if (test09()) System.out.println("Test 9 Passed");
			else System.out.println("Test 9 Failed");
		} catch (Exception e) { System.out.println("Test 9 Failed (exception)"); }
		
		// remove digit
		try {
			if (test10()) System.out.println("Test 10 Passed");
			else System.out.println("Test 10 Failed");
		} catch (Exception e) { System.out.println("Test 10 Failed (exception)"); }

		 // Test 11: Test conversion from hexadecimal to binary
        try {
            LinkedNumber ln1 = new LinkedNumber("1A3F", 16);
            boolean test11 = ln1.convert(2).toString().equals("110100111111");
            System.out.println("Test 11 Passed: " + test11);
        } catch (Exception e) {
            System.out.println("Test 11 Failed (exception): " + e.getMessage());
        }

        // Test 12: Test conversion from binary to octal
        try {
            LinkedNumber ln2 = new LinkedNumber("10110111001", 2);
            boolean test12 = ln2.convert(8).toString().equals("13371");
            System.out.println("Test 12 Passed: " + test12);
        } catch (Exception e) {
            System.out.println("Test 12 Failed (exception): " + e.getMessage());
        }

        // Test 13: Test adding digit to the beginning of the number
        try {
            LinkedNumber ln3 = new LinkedNumber("54321", 10);
            ln3.addDigit(new Digit('9'), 0);
            boolean test13 = ln3.toString().equals("954321");
            System.out.println("Test 13 Passed: " + test13);
        } catch (Exception e) {
            System.out.println("Test 13 Failed (exception): " + e.getMessage());
        }

        // Test 14: Test removing digit from the middle of the number
        try {
            LinkedNumber ln4 = new LinkedNumber("12345", 10);
            int removedVal = ln4.removeDigit(2);
            boolean test14 = ln4.toString().equals("1245") && removedVal == 300;
            System.out.println("Test 14 Passed: " + test14);
        } catch (Exception e) {
            System.out.println("Test 14 Failed (exception): " + e.getMessage());
        }

        // Test 15: Test converting an octal number to decimal
        try {
            LinkedNumber ln5 = new LinkedNumber("726", 8);
            boolean test15 = ln5.convert(10).toString().equals("470");
            System.out.println("Test 15 Passed: " + test15);
        } catch (Exception e) {
            System.out.println("Test 15 Failed (exception): " + e.getMessage());
        }

        // Test 16: Test adding digit to the end of the number
        try {
            LinkedNumber ln6 = new LinkedNumber("2468", 10);
            ln6.addDigit(new Digit('0'), ln6.getNumDigits());
            boolean test16 = ln6.toString().equals("24680");
            System.out.println("Test 16 Passed: " + test16);
        } catch (Exception e) {
            System.out.println("Test 16 Failed (exception): " + e.getMessage());
        }

        // Test 17: Test removing digit from the end of the number
        try {
            LinkedNumber ln7 = new LinkedNumber("13579", 10);
            int removedVal2 = ln7.removeDigit(ln7.getNumDigits() - 1);
            boolean test17 = ln7.toString().equals("1357") && removedVal2 == 9;
            System.out.println("Test 17 Passed: " + test17);
        } catch (Exception e) {
            System.out.println("Test 17 Failed (exception): " + e.getMessage());
        }

        // Test 18: Test converting a binary number to hexadecimal
        try {
            LinkedNumber ln8 = new LinkedNumber("110010100111", 2);
            boolean test18 = ln8.convert(16).toString().equals("6957");
            System.out.println("Test 18 Passed: " + test18);
        } catch (Exception e) {
            System.out.println("Test 18 Failed (exception): " + e.getMessage());
        }

        // Test 19: Test equals method for unequal linked numbers
        try {
            LinkedNumber ln9 = new LinkedNumber("12345", 10);
            LinkedNumber ln10 = new LinkedNumber("54321", 10);
            boolean test19 = !ln9.equals(ln10);
            System.out.println("Test 19 Passed: " + test19);
        } catch (Exception e) {
            System.out.println("Test 19 Failed (exception): " + e.getMessage());
        }

        // Test 20: Test equals method for equal linked numbers
        try {
            LinkedNumber ln11 = new LinkedNumber("12345", 10);
            LinkedNumber ln12 = new LinkedNumber("12345", 10);
            boolean test20 = ln11.equals(ln12);
            System.out.println("Test 20 Passed: " + test20);
        } catch (Exception e) {
            System.out.println("Test 20 Failed (exception): " + e.getMessage());
        }
        // Test 21: Test conversion from decimal to binary
        try {
            LinkedNumber ln1 = new LinkedNumber(123);
            boolean test21 = ln1.convert(2).toString().equals("1111011");
            System.out.println("Test 21 Passed: " + test21);
        } catch (Exception e) {
            System.out.println("Test 21 Failed (exception): " + e.getMessage());
        }

        // Test 22: Test conversion from binary to hexadecimal
        try {
            LinkedNumber ln2 = new LinkedNumber("101010101", 2);
            boolean test22 = ln2.convert(16).toString().equals("155");
            System.out.println("Test 22 Passed: " + test22);
        } catch (Exception e) {
            System.out.println("Test 22 Failed (exception): " + e.getMessage());
        }

        // Test 23: Test adding digit at a specified position
        try {
            LinkedNumber ln3 = new LinkedNumber("2468", 10);
            ln3.addDigit(new Digit('5'), 2);
            boolean test23 = ln3.toString().equals("25468");
            System.out.println("Test 23 Passed: " + test23);
        } catch (Exception e) {
            System.out.println("Test 23 Failed (exception): " + e.getMessage());
        }

        // Test 24: Test removing digit from an empty linked number
        try {
            LinkedNumber ln4 = new LinkedNumber("", 10);
            int removedVal = ln4.removeDigit(0); // Removing from an empty number
            boolean test24 = ln4.toString().equals("") && removedVal == 0;
            System.out.println("Test 24 Passed: " + test24);
        } catch (Exception e) {
            System.out.println("Test 24 Failed (exception): " + e.getMessage());
        }

        // Test 25: Test converting a hexadecimal number to octal
        try {
            LinkedNumber ln5 = new LinkedNumber("1A3F", 16);
            boolean test25 = ln5.convert(8).toString().equals("6437");
            System.out.println("Test 25 Passed: " + test25);
        } catch (Exception e) {
            System.out.println("Test 25 Failed (exception): " + e.getMessage());
        }

        // Test 26: Test adding digit at the end of the number
        try {
            LinkedNumber ln6 = new LinkedNumber("12345", 10);
            ln6.addDigit(new Digit('6'), ln6.getNumDigits());
            boolean test26 = ln6.toString().equals("123456");
            System.out.println("Test 26 Passed: " + test26);
        } catch (Exception e) {
            System.out.println("Test 26 Failed (exception): " + e.getMessage());
        }

        // Test 27: Test removing digit at the beginning of the number
        try {
            LinkedNumber ln7 = new LinkedNumber("98765", 10);
            int removedVal2 = ln7.removeDigit(0);
            boolean test27 = ln7.toString().equals("8765") && removedVal2 == 90000;
            System.out.println("Test 27 Passed: " + test27);
        } catch (Exception e) {
            System.out.println("Test 27 Failed (exception): " + e.getMessage());
        }

        // Test 28: Test converting an octal number to hexadecimal
        try {
            LinkedNumber ln8 = new LinkedNumber("7654", 8);
            boolean test28 = ln8.convert(16).toString().equals("3D4");
            System.out.println("Test 28 Passed: " + test28);
        } catch (Exception e) {
            System.out.println("Test 28 Failed (exception): " + e.getMessage());
        }

        // Test 29: Test equals method with null linked number
        try {
            LinkedNumber ln9 = new LinkedNumber("12345", 10);
            boolean test29 = !ln9.equals(null);
            System.out.println("Test 29 Passed: " + test29);
        } catch (Exception e) {
            System.out.println("Test 29 Failed (exception): " + e.getMessage());
        }

        // Test 30: Test equals method with the same linked number instance
        try {
            LinkedNumber ln10 = new LinkedNumber("54321", 10);
            boolean test30 = ln10.equals(ln10);
            System.out.println("Test 30 Passed: " + test30);
        } catch (Exception e) {
            System.out.println("Test 30 Failed (exception): " + e.getMessage());
        }
	}
	
	
	
	private static String traverseForward (DLNode<Digit> front) {
		String s = "";
		DLNode<Digit> curr = front;
		while (curr != null) {
			s += curr.getElement();
			curr = curr.getNext();
		}
		return s;
	}

	private static String traverseBackward (DLNode<Digit> rear) {
		String s = "";
		DLNode<Digit> curr = rear;
		while (curr != null) {
			s += curr.getElement();
			curr = curr.getPrev();
		}
		return s;
	}

}
