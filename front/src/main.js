import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import iView from "iview";
import "iview/dist/styles/iview.css";
import CustomLayout from "components/common/custom-layout";

Vue.config.productionTip = false;

Vue.use(iView);

Vue.component("custom-layout", CustomLayout);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
