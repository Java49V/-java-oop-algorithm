package telran.util.test;

//import static org.junit.jupiter.api.Assertions.*;

//import java.util.Arrays;

//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;

import telran.util.*;

class ArrayListTest extends ListTest {

	@Override
	protected <T> List<T> getList() {		
		return new ArrayList<>();
	}
	
}
