#include <bits/stdc++.h>
#include <pthread.h>

using namespace std;

struct node{
    int a, b, id;
    double sum;
};

void *go(void *arg){
    node *p = (node*)arg;
    double sum = 0;
    //printf("%d %d\n", p->a, p->b);
    for(int i = p->a; i < p->b; i++){
        sum += (1.00 / (i*i));
    }
    p->sum = sum;
    printf("From threas %d: %f\n", p->id, p->sum);
}

int main(){
    int n;
    pthread_t *ptr;
    printf("Enter n: ");
    scanf("%d", &n);
    int num_of_threads;
    int x = sqrt(n);
    for(; x > 0; x--){
        if(n % x == 0)
        {
            num_of_threads = x;
            break;
        }
    }
    ptr = (pthread_t*)malloc(num_of_threads*sizeof(pthread_t));
    int size = n / num_of_threads;
    node tmp[num_of_threads];
    int a = 1;
    for(int i = 0; i < num_of_threads; i++){
        tmp[i].id = i;
        tmp[i].a = a;
        a += size;
        tmp[i].b = a;
        //printf("%d %d\n", tmp[i].a, tmp[i].b);
        int rc = pthread_create(&ptr[i], NULL, go, (void*)(&tmp[i]));
        pthread_join(ptr[i], NULL);
    }
    double sum = 0;
    for(int i = 0; i < num_of_threads; i++){
        sum += tmp[i].sum;
    }
    //cout << sum << endl;
    double pi = sqrt(6*sum);
    printf("pi: %.4f\n", pi);
    pthread_exit(NULL);
}
