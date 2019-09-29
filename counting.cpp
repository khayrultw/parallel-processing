#include <bits/stdc++.h>
#include <pthread.h>

using namespace std;

int MAX, k;
int *arr;
int *sorted = NULL;

pthread_mutex_t lockk;

struct node{
    int a, b;
};

int *merge(int *a, int m, int *b, int n) {
   if(a == NULL)
    {
        return b;
    }
  int i, j, k;
  j = k = 0;

  int *sorted = (int*)malloc(n*m*sizeof(int));
  for (i = 0; i < m + n;) {
    if (j < m && k < n) {
      if (a[j] < b[k]) {
        sorted[i] = a[j];
        j++;
      }
      else {
        sorted[i] = b[k];
        k++;
      }
      i++;
    }
    else if (j == m) {
      for (; i < m + n;) {
        sorted[i] = b[k];
        k++;
        i++;
      }
    }
    else {
      for (; i < m + n;) {
        sorted[i] = a[j];
        j++;
        i++;
      }
    }
  }
  return sorted;
}

void *go(void *arg){
   
    node *p = (node*)arg;
    int sz = p->b - p->a;
    int *cnt = (int*)malloc((1+MAX)*sizeof(int));
    memset(cnt, 0, sizeof(cnt));

    k++;
    printf("\nThread %d has started sorting.\n", k);
    for(int i = p->a; i < p->b; i++){
        cnt[arr[i]]++;
    }
    //printf("OG\n");
    int *a = (int*)malloc(sz*sizeof(int));
    int l = 0;
    for(int  i = 0; i <= MAX; i++){
        for(int j = 0; j < cnt[i]; j++){
            a[l++] = i;
            //printf("%d ", i);
        }
        //printf("\n");

    }
    pthread_mutex_lock(&lockk);
    sorted = merge(sorted, (k-1)*sz,  a, sz);
    pthread_mutex_unlock(&lockk);
    //printf("ooo\n");
    for(int i = 0; i < k*sz; i++)
        printf("%d ", sorted[i]);
    
    printf("\nThread %d has finished sorting.\n\n", k);
}

int main(){
    if (pthread_mutex_init(&lockk, NULL) != 0) 
	{ 
		printf("\n mutex init has failed\n"); 
		return 1; 
	} 
    int n;
    k = 0;
    pthread_t *ptr;

    printf("Enter n: ");
    scanf("%d", &n);

    printf("Enter maximum value: ");
    scanf("%d", &MAX);
    
    arr = (int*)malloc((1+n)*sizeof(int));

    printf("Enter data: ");
    for(int i = 0; i < n; i++){
        scanf("%d", &arr[i]);
    }

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

    int a = 0;
    for(int i = 0; i < num_of_threads; i++){
        tmp[i].a = a;
        a += size;
        tmp[i].b = a;
        //printf("%d %d\n", tmp[i].a, tmp[i].b);
        int rc = pthread_create(&ptr[i], NULL, go, (void*)(&tmp[i]));
        pthread_join(ptr[i], NULL);
    }

    for(int i = 0; i < n; i++)
        printf("%d ", sorted[i]);
    printf("\n");
    pthread_mutex_destroy(&lockk); 
    pthread_exit(NULL);
}