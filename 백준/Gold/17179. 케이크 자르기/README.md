# [Gold IV] 케이크 자르기 - 17179 

[문제 링크](https://www.acmicpc.net/problem/17179) 

### 성능 요약

메모리: 16160 KB, 시간: 172 ms

### 분류

이분 탐색, 매개 변수 탐색

### 제출 일자

2025년 7월 29일 20:40:56

### 문제 설명

<p>생일을 맞이한 주성이가 생일 파티를 준비하려고 한다. 주성이는 일반 케이크 대신 평소 좋아하던 롤 케이크를 준비했다. 롤 케이크에는 장식이 존재해서 특정 위치에서만 자를 수 있다. 주성이는 롤 케이크 조각을 파티에 올 친구의 수 만큼 준비하고 싶어서, 가장 작은 조각의 크기를 미리 알아보기로 했다. 하지만 짓궂은 주성이의 친구들은 생일파티에 몇 명이 참석하는지 직접적으로 알려주지를 않는다. 그래서 몇 개의 수를 목록에 적어, 각 수만큼 조각을 만들었을 때 가장 작은 조각의 길이의 최댓값을 구하려고 한다.</p>

<p>예를 들어 70cm의 롤 케이크에 자를 수 있는 지점이 5군데(10cm, 20cm, 35cm, 55cm, 60cm)가 있다고 하자. 만약 목록에 적힌 수 중 하나가 3이라면 이때 가장 작은 조각의 길이는 최대 15cm이다. 예를 들어 20cm, 35cm, 55cm 지점을 자를 때 최대가 된다.</p>

### 입력 

 <p>첫 번째 줄에 자르는 횟수가 담긴 목록의 길이 N과 자를 수 있는 지점의 개수 M, 그리고 롤 케이크의 길이인 정수 L이 주어진다. (1 ≤ N ≤ M ≤ 1,000, 1 < L ≤ 4,000,000)</p>

<p>다음 M줄에 걸쳐 자를 수 있는 지점을 나타내는 정수 S<sub>i</sub>가 주어진다. (1 ≤ S<sub>i</sub> < L)</p>

<p>다음 N줄에 걸쳐 자르는 횟수를 나타내는 정수 Q<sub>i</sub>가 주어진다. (1 ≤ Q<sub>i</sub> ≤ M)</p>

<p>S<sub>i</sub>는 오름차순으로 주어지고 중복되는 수는 없으며, Q<sub>i</sub>도 마찬가지이다.</p>

### 출력 

 <p>N개 줄에 걸쳐 각 목록에 있는 횟수대로 롤 케이크를 잘랐을 때 가장 작은 조각의 길이의 최댓값을 출력한다.</p>

