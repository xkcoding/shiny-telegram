export default [
  {
    path: "/",
    redirect: "/app"
  },
  {
    path: "/app",
    name: "app",
    component: () => import("views/App.vue")
  },
  {
    path: "/push",
    name: "push",
    component: () => import("views/Push.vue")
  },
  {
    path: "/dev",
    name: "dev",
    component: () => import("views/Dev.vue")
  },
  {
    path: "/manage",
    name: "manage",
    component: () => import("views/Manage.vue")
  }
];
