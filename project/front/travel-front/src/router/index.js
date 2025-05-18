import {createRouter, createWebHistory} from 'vue-router';

const routes = [
    {
        path: '/',
        name: 'Home',
        component: () => import('@/pages/Home.vue'), // 默认主页
    },
    {
        path: '/search',
        name: 'Search',
        component: () => import('@/pages/Search.vue'),
    },
    // 继续补充其他页面
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;

