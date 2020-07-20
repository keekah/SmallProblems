#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int n, p, q;
char same[13][2];
char input[51] = {};

void processInput();

int main(void)
{
	scanf("%d\n", &n);
	for (int loop = 1; loop <= n; loop++)
	{
		scanf("%d\n", &p);
		int i;
		for (i=0; i < p; i++)
			scanf("%c %c\n", &same[i][0], &same[i][1]);

      printf("Test case #%d:\n", loop);

		scanf("%d\n", &q);
		for (i=0; i < q; i++)
			processInput();
         
      printf("\n");
	}

	return 0;
}

void processInput()
{
	char tupin[51] = {};
	int i, j;
	scanf("%s", input);

	printf("%s ", input);
	for (i = 0; i < strlen(input); i++)
		for (j = 0; j < p; j++)
			if (input[i] == same[j][0])
				input[i] = same[j][1];

	for (i = 0; i < strlen(input); i++)
		tupin[i] = input[strlen(input)-1-i];

	if (strcmp(tupin, input) == 0)
		printf("YES\n");
	else 	printf("NO\n");
}