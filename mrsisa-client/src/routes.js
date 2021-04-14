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
      path: "/registracija/zaposleni",
      name: "RegistracijaZaposlenog",
      component: () => import("./components/Admin/RegZaposlenog")
    },
    {
      path: "/pacijent/zalba/apoteka",
      name: "Zalba na apoteku",
      component: () => import("./components/Admin/ZalbaNaApoteku")
    },
    {
      path: "/alergija",
      name: "DodajAlergiju",
      component: () => import("./components/DodajAlergiju")
    },
    {
      path: "/apoteke",
      name: "ListApoteke",
      component: () => import("./components/ListApoteke")
    },
    {
      path: "/apoteke/kreiraj",
      name: "RegApoteka",
      component: () => import("./components/Admin/RegApoteka")
    },
    {
      path: "/lekovi/kreiraj",
      name: "KreirajLek",
      component: () => import("./components/Admin/KreirajLek")
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
      path: "/farmaceut",
      name: "HomepageDF",
      component: () => import("./components/HomepageDF")
    },
    {
      path: "/dermatolog",
      name: "HomepageDF",
      component: () => import("./components/HomepageDF")
    },
    {
      path: "/adminApoteke",
      name: "HomepageAdminApoteke",
      component: () => import("./components/HomepageAdminApoteke")
    }
  ]
});

export default router;