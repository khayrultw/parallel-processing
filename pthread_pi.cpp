#include <bits/stdc++.h>
#include <pthread.h>

using namespace std;

typedef struct node
{
    int a, b, id;
    float sum;
} node;

void *go(void *p)
{
    node *x = (node *)p;
    float sum = 0;
    for (int i = x->a; i < x->b; i++)
    {
        float temp = 1.00 / (2 * i + 1);
        if (i % 2)
            temp *= -1;
        sum += temp;
    }
    sum *= 4;
    x->sum = sum;
    printf("sum calculated from thread %d is %.10f\n", x->id, sum);
}

int main()
{
    int num_of_thread, n;
    printf("Number of thread: ");
    scanf("%d", &num_of_thread);

    printf("Number of term: ");
    scanf("%d", &n);

    node tmp[num_of_thread];
    int per_thread = n / num_of_thread;
    //int extra = n%num_of_thread;
    //printf("%d\n", per_thread);
    pthread_t thread[num_of_thread];

    int l = 0;
    for (int i = 0; i < num_of_thread; i++)
    {
        tmp[i].a = l;
        l = tmp[i].b = tmp[i].a + per_thread;
        tmp[i].id = i;
        pthread_create(&thread[i], NULL, go, (void *)(&tmp[i]));
        pthread_join(thread[i], NULL);
    }

    float sum = 0;
    for (int i = 0; i < num_of_thread; i++)
        sum += tmp[i].sum;

    printf("Value of pi: %.10f\n", sum);
    return 0;
}