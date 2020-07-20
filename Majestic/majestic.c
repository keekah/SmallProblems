#include <stdio.h>

int main(void)
{
	int n, a, b, c, count = 0;

	scanf("%d", &n);
	while (n > 0)
	{
		scanf("%d %d %d", &a, &b, &c);
		printf("%d %d %d\n", a, b, c);
		if (a >= 10) count++;
		if (b >= 10) count++;
		if (c >= 10) count++;

		if (count == 0) printf("zilch\n");
		if (count == 1) printf("double\n");
		if (count == 2) printf("double-double\n");
		if (count == 3) printf("triple-double\n");
		printf("\n");
		count = 0;
		n--;
	}	

	return 0;
}