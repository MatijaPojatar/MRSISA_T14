import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

const router = new Router({
  mode: "history", // Use browser history
  routes: [
    {
      path: "/",
      name: "Prijava",
      component: () => import("./components/Prijava")
    },
    {
      path: "/prijava",
      name: "Prijava",
      component: () => import("./components/Prijava")
    },
    {
      path: "/registracija",
      name: "Registracija",
      component: () => import("./components/Registracija")
    },
    {
      path: "/lekovi",
      name: "Lekovi",
      component: () => import("./components/LekApp")
    },
    {
      path: "/lekovi/:sifra",
      name: "Lek Details",
      component: () => import("./components/LekComponent")
    },
    {
      path: "/rezervacija/pretraga",
      name: "Pretraga Rezervacija",
      component: () => import("./components/PretragaRezervacija")
    },
    {
      path: "/rezervacija",
      name: "Rezervacije",
      component: () => import("./components/RezApp")
    },
    {
      path: "/apoteka/lekovi",
      name: "Lekovi u apoteci",
      component: () => import("./components/ApotekaApp")
    }
  ]
});

export default router;