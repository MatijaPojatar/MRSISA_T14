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
      path: "/apoteke",
      name: "Registracija",
      component: () => import("./components/ListApoteke")
    },
    {
      path: "/lekovi/kreiraj",
      name: "KreirajLek",
      component: () => import("./components/KreirajLek")
    },
    {
      path: "/lekovi",
      name: "Lekovi",
      component: () => import("./components/LekApp")
    },
    {
      path: "/lekovi/lek/:sifra",
      name: "Lek Details",
      component: () => import("./components/LekComponent")
    },
    {
      path: "/lekovi/pretraga",
      name: "Pretraga Lekova",
      component: () => import("./components/PretragaLekova")
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
    },
    {
      path: "/dermatolog/kalendar",
      name: "RadniKalendarDermatolog",
      component: () => import("./components/RadniKalendarDermatolog")
    },
    {
      path: "/farmaceut/kalendar",
      name: "RadniKalendarFarmaceut",
      component: () => import("./components/RadniKalendarFarmaceut")
    }
  ]
});

export default router;