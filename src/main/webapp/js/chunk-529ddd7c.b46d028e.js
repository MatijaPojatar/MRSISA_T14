(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-529ddd7c"],{"0de5":function(e,t,i){"use strict";i("a9e3"),i("d81d"),i("fb6a"),i("96a9");var n=i("24b2"),a=i("53ca");function s(e,t){var i=t.modifiers||{},n=i.self,s=void 0!==n&&n,r=t.value,o="object"===Object(a["a"])(r)&&r.options||{passive:!0},l="function"===typeof r||"handleEvent"in r?r:r.handler,u=s?e:t.arg?document.querySelector(t.arg):window;u&&(u.addEventListener("scroll",l,o),e._onScroll={handler:l,options:o,target:s?void 0:u})}function r(e){if(e._onScroll){var t=e._onScroll,i=t.handler,n=t.options,a=t.target,s=void 0===a?e:a;s.removeEventListener("scroll",i,n),delete e._onScroll}}var o={inserted:s,unbind:r},l=o,u=i("80d2");t["a"]=n["a"].extend({name:"v-virtual-scroll",directives:{Scroll:l},props:{bench:{type:[Number,String],default:0},itemHeight:{type:[Number,String],required:!0},items:{type:Array,default:function(){return[]}}},data:function(){return{first:0,last:0,scrollTop:0}},computed:{__bench:function(){return parseInt(this.bench,10)},__itemHeight:function(){return parseInt(this.itemHeight,10)},firstToRender:function(){return Math.max(0,this.first-this.__bench)},lastToRender:function(){return Math.min(this.items.length,this.last+this.__bench)}},watch:{height:"onScroll",itemHeight:"onScroll"},mounted:function(){this.last=this.getLast(0)},methods:{getChildren:function(){return this.items.slice(this.firstToRender,this.lastToRender).map(this.genChild)},genChild:function(e,t){t+=this.firstToRender;var i=Object(u["g"])(t*this.__itemHeight);return this.$createElement("div",{staticClass:"v-virtual-scroll__item",style:{top:i},key:t},Object(u["s"])(this,"default",{index:t,item:e}))},getFirst:function(){return Math.floor(this.scrollTop/this.__itemHeight)},getLast:function(e){var t=parseInt(this.height||0,10)||this.$el.clientHeight;return e+Math.ceil(t/this.__itemHeight)},onScroll:function(){this.scrollTop=this.$el.scrollTop,this.first=this.getFirst(),this.last=this.getLast(this.first)}},render:function(e){var t=e("div",{staticClass:"v-virtual-scroll__container",style:{height:Object(u["g"])(this.items.length*this.__itemHeight)}},this.getChildren());return e("div",{staticClass:"v-virtual-scroll",style:this.measurableStyles,directives:[{name:"scroll",modifiers:{self:!0},value:this.onScroll}],on:this.$listeners},[t])}})},"1c58":function(e,t,i){},"2bfd":function(e,t,i){},5559:function(e,t,i){"use strict";i.r(t);var n=function(){var e=this,t=e.$createElement,i=e._self._c||t;return e.nastavi?i("v-card",[i("v-card-title",{staticClass:"display-1"},[e._v("Registracija Zaposlenog")]),i("v-card-text",[i("v-form",{ref:"forma",attrs:{"lazy-validation":""},model:{value:e.valid,callback:function(t){e.valid=t},expression:"valid"}},[i("v-row",[i("v-col",[i("v-text-field",{attrs:{rules:[e.rules.required,e.rules.email],label:"Email",required:""},model:{value:e.email,callback:function(t){e.email=t},expression:"email"}}),i("v-text-field",{attrs:{rules:[e.rules.required],label:"Ime",required:""},model:{value:e.ime,callback:function(t){e.ime=t},expression:"ime"}}),i("v-text-field",{attrs:{rules:[e.rules.required],label:"Prezime",required:""},model:{value:e.prezime,callback:function(t){e.prezime=t},expression:"prezime"}}),i("v-text-field",{attrs:{rules:[e.rules.required,e.rules.telefon],label:"Broj telefona",required:""},model:{value:e.brojTelefona,callback:function(t){e.brojTelefona=t},expression:"brojTelefona"}}),i("v-select",{attrs:{items:e.polovi,label:"Pol",required:""},model:{value:e.pol,callback:function(t){e.pol=t},expression:"pol"}}),i("v-text-field",{attrs:{rules:[e.rules.required],label:"Adresa",required:""},model:{value:e.adresa,callback:function(t){e.adresa=t},expression:"adresa"}}),i("v-text-field",{attrs:{rules:[e.rules.required],label:"Grad",required:""},model:{value:e.grad,callback:function(t){e.grad=t},expression:"grad"}}),i("v-text-field",{attrs:{rules:[e.rules.required],label:"Država",required:""},model:{value:e.drzava,callback:function(t){e.drzava=t},expression:"drzava"}})],1),i("v-col",[i("v-text-field",{attrs:{value:"Datum Rođenja",readonly:""}}),i("v-date-picker",{model:{value:e.datum,callback:function(t){e.datum=t},expression:"datum"}})],1),i("v-col",[e.uloga===e.uloge[0]?i("v-text-field",{attrs:{label:"Potpis"},model:{value:e.potpis,callback:function(t){e.potpis=t},expression:"potpis"}}):e._e(),e.uloga===e.uloge[1]?i("v-autocomplete",{attrs:{items:e.allApoteke,label:"Apoteka","item-text":"naziv","item-value":"id",required:""},scopedSlots:e._u([{key:"selection",fn:function(t){return[e._v(" "+e._s(t.item.naziv)+" ")]}},{key:"item",fn:function(t){return[e._v(" "+e._s(t.item.naziv)+" ")]}}],null,!1,3019413340),model:{value:e.izabranaApotekaId,callback:function(t){e.izabranaApotekaId=t},expression:"izabranaApotekaId"}}):e._e(),e.uloga===e.uloge[2]?i("v-time-picker",{staticClass:"mt-4",attrs:{format:"ampm",scrollable:""},model:{value:e.pocetakRadnogVremena,callback:function(t){e.pocetakRadnogVremena=t},expression:"pocetakRadnogVremena"}}):e._e(),e.uloga===e.uloge[2]?i("v-time-picker",{staticClass:"mt-4",attrs:{format:"ampm",scrollable:""},model:{value:e.krajRadnogVremena,callback:function(t){e.krajRadnogVremena=t},expression:"krajRadnogVremena"}}):e._e(),e.uloga===e.uloge[3]?i("v-text-field",{staticClass:"blue--text",attrs:{label:"Naziv preduzeća"},model:{value:e.nazivPreduzeca,callback:function(t){e.nazivPreduzeca=t},expression:"nazivPreduzeca"}}):e._e()],1)],1)],1)],1),i("v-card-actions",[i("v-btn",{staticClass:"grey white--text",on:{click:e.goBack}},[e._v("Odustani")]),i("v-spacer"),e.uloga===e.uloge[0]?i("v-btn",{staticClass:"blue white--text",attrs:{disabled:!e.valid},on:{click:e.onSubmitAS}},[e._v(" Registracija Administratora sistema")]):e._e(),e.uloga===e.uloge[1]?i("v-btn",{staticClass:"blue white--text",attrs:{disabled:!e.valid},on:{click:e.onSubmitAAP}},[e._v(" Registracija Administratora apoteke")]):e._e(),e.uloga===e.uloge[2]?i("v-btn",{staticClass:"blue white--text",attrs:{disabled:!e.valid},on:{click:e.onSubmitDerm}},[e._v(" Registracija dermatologa")]):e._e(),e.uloga===e.uloge[3]?i("v-btn",{staticClass:"blue white--text",attrs:{disabled:!e.valid},on:{click:e.onSubmitDob}},[e._v(" Registracija Dobavljača")]):e._e()],1),i("v-dialog",{attrs:{persistent:"","max-width":"500"},model:{value:e.odabir,callback:function(t){e.odabir=t},expression:"odabir"}},[i("v-card",[i("v-container",[i("v-row",[i("v-list-item",[i("v-chip",{attrs:{dark:"",color:"grey"}},[e._v("Uloga zaposlenog")]),i("v-spacer"),i("v-chip",{staticClass:"ml-3 mr-3",attrs:{dark:"",color:"grey"}},[e._v("Odaberi")])],1)],1),i("v-row",[i("v-virtual-scroll",{attrs:{items:e.uloge,"item-height":50,height:"300"},scopedSlots:e._u([{key:"default",fn:function(t){var n=t.item;return[i("v-list-item",[i("v-list-item-content",[i("v-list-item-title",{staticClass:"text-left"},[e._v(e._s(n.naziv))])],1),i("v-spacer"),i("v-list-item-action",[i("v-btn",{attrs:{red:""},on:{click:function(t){return e.odaberiUlogu(n)}}},[i("v-icon",[e._v("mdi-plus-thick")])],1)],1)],1)]}}],null,!1,2485311349)})],1)],1),i("v-card-actions",[i("v-btn",{staticClass:"blue white--text",on:{click:e.goBack}},[e._v("Cancel")])],1)],1)],1)],1):e._e()},a=[],s=i("1da1"),r=i("5530"),o=(i("96cf"),i("2f62")),l={name:"RegistracijaZaposlenog",data:function(){return{izabranaApotekaId:-1,odabir:!0,uloga:{},nastavi:!0,uloge:[{id:1,naziv:"Administrator sistema"},{id:2,naziv:"Administrator apoteke"},{id:3,naziv:"Dermatolog"},{id:4,naziv:"Dobavljač"}],polovi:["MUSKI","ZENSKI"],email:"",lozinka:"",potvrdaLozinke:"",ime:"",prezime:"",brojTelefona:"",datum:"",pol:"",adresa:"",grad:"",drzava:"",pocetakRadnogVremena:"",krajRadnogVremena:"",nazivPreduzeca:"",potpis:"",apotekeGdeJeZaposlenString:"",apotekeGdeJeZaposlen:[],valid:!1,show1:!1,show2:!1,rules:{required:function(e){return!!e||"Obavezno polje"},email:function(e){return/.+@.+\..+/.test(e)||"Email nije validan"},telefon:function(e){return/^\d+$/.test(e)||"Broj telefona nije validan"},counter:function(e){return 10==e.length||"Datum nije dobar"},counterVreme:function(e){return 5==e.length||"Vreme nije dobro"}}}},computed:Object(r["a"])(Object(r["a"])({},Object(o["c"])({allApoteke:"apoteke/getAllApotekeMinimal"})),{},{show:{get:function(){return this.value},set:function(e){this.$emit("input",e)}}}),mounted:function(){var e=this;return Object(s["a"])(regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return t.next=2,e.getAllApotekeMinimalAction();case 2:case"end":return t.stop()}}),t)})))()},methods:Object(r["a"])(Object(r["a"])({},Object(o["b"])({getAllApotekeMinimalAction:"apoteke/getAllApotekeMinimalAction",dermSignUpAction:"korisnici/dermSignUpAction",dobSignUpAction:"korisnici/dobSignUpAction",aApSignUpAction:"korisnici/aApSignUpAction",asSignUpAction:"korisnici/asSignUpAction"})),{},{goBack:function(){this.cancel(),this.nastavi=!1},onSubmitAAP:function(){var e=this;return Object(s["a"])(regeneratorRuntime.mark((function t(){var i;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:if(-1!==e.izabranaApotekaId){t.next=3;break}return alert("Potrebno je odabrati apoteku gde je admin zaposlen!"),t.abrupt("return");case 3:return i={ime:e.ime,prezime:e.prezime,mail:e.email,adresa:e.adresa,grad:e.grad,drzava:e.drzava,brojTelefona:e.brojTelefona,pol:e.pol,datumRodjenja:e.datum,apotekaId:e.izabranaApotekaId,promenjenaLozinka:!1,enabled:!1},t.prev=4,t.next=7,e.aApSignUpAction(i);case 7:e.cancel(),console.log(i),t.next=14;break;case 11:t.prev=11,t.t0=t["catch"](4),alert("Greska pri registraciji admina apoteke");case 14:case"end":return t.stop()}}),t,null,[[4,11]])})))()},onSubmitAS:function(){var e=this;return Object(s["a"])(regeneratorRuntime.mark((function t(){var i;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:if(alert("Submit Admin sistema klik"),""!==e.potpis){t.next=4;break}return alert("Potrebno uneti potpis administratora"),t.abrupt("return");case 4:return i={ime:e.ime,prezime:e.prezime,mail:e.email,adresa:e.adresa,grad:e.grad,drzava:e.drzava,brojTelefona:e.brojTelefona,pol:e.pol,datumRodjenja:e.datum,potpis:e.potpis,promenjenaLozinka:!1,enabled:!1},console.log("OVO SI POSLALA"),console.log(i),t.prev=7,t.next=10,e.asSignUpAction(i);case 10:e.cancel(),console.log(i),t.next=17;break;case 14:t.prev=14,t.t0=t["catch"](7),alert("Greska pri registraciji admina sistema");case 17:case"end":return t.stop()}}),t,null,[[7,14]])})))()},odaberiUlogu:function(e){this.odabir=!1,this.uloga=e},onSubmitDerm:function(){var e=this;return Object(s["a"])(regeneratorRuntime.mark((function t(){var i;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:if(""!==e.pocetakRadnogVremena&&""!==e.krajRadnogVremena){t.next=3;break}return alert("Potreban unos radnog vremena"),t.abrupt("return");case 3:return i={ime:e.ime,prezime:e.prezime,mail:e.email,adresa:e.adresa,grad:e.grad,drzava:e.drzava,brojTelefona:e.brojTelefona,pol:e.pol,datumRodjenja:e.datum,pocetakRadnogVremena:e.pocetakRadnogVremena,krajRadnogVremena:e.krajRadnogVremena,ocena:0,promenjenaLozinka:!1,enabled:!1},t.prev=4,t.next=7,e.dermSignUpAction(i);case 7:e.cancel(),console.log(i),t.next=14;break;case 11:t.prev=11,t.t0=t["catch"](4),alert("Greska pri registraciji dermatologa");case 14:case"end":return t.stop()}}),t,null,[[4,11]])})))()},onSubmitDob:function(){var e=this;return Object(s["a"])(regeneratorRuntime.mark((function t(){var i;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:if(""!==e.nazivPreduzeca){t.next=3;break}return alert("Potreban unos naziva preduzeca!"),t.abrupt("return");case 3:return i={ime:e.ime,prezime:e.prezime,mail:e.email,adresa:e.adresa,grad:e.grad,drzava:e.drzava,brojTelefona:e.brojTelefona,pol:e.pol,datumRodjenja:e.datum,nazivPreduzeca:e.nazivPreduzeca,promenjenaLozinka:!1,enabled:!1},t.prev=4,t.next=7,e.dobSignUpAction(i);case 7:e.cancel(),console.log(i),t.next=14;break;case 11:t.prev=11,t.t0=t["catch"](4),alert("Greska pri registraciji dobavljaca");case 14:case"end":return t.stop()}}),t,null,[[4,11]])})))()},cancel:function(){this.$refs.forma.reset(),this.$refs.forma.resetValidation()}})},u=l,c=i("2877"),h=i("6544"),d=i.n(h),m=i("c6a6"),p=i("8336"),f=i("b0af"),v=i("99d9"),g=i("cc20"),b=i("62ad"),k=i("a523"),S=i("2e4b"),y=i("169a"),I=i("4bd4"),x=i("132d"),w=i("da13"),A=i("1800"),j=i("5d23"),M=i("0fd9"),C=i("b974"),V=i("2fa4"),z=i("8654"),_=i("c964"),P=i("0de5"),D=Object(c["a"])(u,n,a,!1,null,null,null);t["default"]=D.exports;d()(D,{VAutocomplete:m["a"],VBtn:p["a"],VCard:f["a"],VCardActions:v["a"],VCardText:v["b"],VCardTitle:v["c"],VChip:g["a"],VCol:b["a"],VContainer:k["a"],VDatePicker:S["a"],VDialog:y["a"],VForm:I["a"],VIcon:x["a"],VListItem:w["a"],VListItemAction:A["a"],VListItemContent:j["a"],VListItemTitle:j["c"],VRow:M["a"],VSelect:C["a"],VSpacer:V["a"],VTextField:z["a"],VTimePicker:_["a"],VVirtualScroll:P["a"]})},"96a9":function(e,t,i){},c6a6:function(e,t,i){"use strict";var n=i("5530"),a=(i("d81d"),i("4de4"),i("498a"),i("7db0"),i("caad"),i("2532"),i("2bfd"),i("b974")),s=i("8654"),r=i("d9f7"),o=i("80d2"),l=Object(n["a"])(Object(n["a"])({},a["b"]),{},{offsetY:!0,offsetOverflow:!0,transition:!1});t["a"]=a["a"].extend({name:"v-autocomplete",props:{allowOverflow:{type:Boolean,default:!0},autoSelectFirst:{type:Boolean,default:!1},filter:{type:Function,default:function(e,t,i){return i.toLocaleLowerCase().indexOf(t.toLocaleLowerCase())>-1}},hideNoData:Boolean,menuProps:{type:a["a"].options.props.menuProps.type,default:function(){return l}},noFilter:Boolean,searchInput:{type:String}},data:function(){return{lazySearch:this.searchInput}},computed:{classes:function(){return Object(n["a"])(Object(n["a"])({},a["a"].options.computed.classes.call(this)),{},{"v-autocomplete":!0,"v-autocomplete--is-selecting-index":this.selectedIndex>-1})},computedItems:function(){return this.filteredItems},selectedValues:function(){var e=this;return this.selectedItems.map((function(t){return e.getValue(t)}))},hasDisplayedItems:function(){var e=this;return this.hideSelected?this.filteredItems.some((function(t){return!e.hasItem(t)})):this.filteredItems.length>0},currentRange:function(){return null==this.selectedItem?0:String(this.getText(this.selectedItem)).length},filteredItems:function(){var e=this;return!this.isSearching||this.noFilter||null==this.internalSearch?this.allItems:this.allItems.filter((function(t){var i=Object(o["r"])(t,e.itemText),n=null!=i?String(i):"";return e.filter(t,String(e.internalSearch),n)}))},internalSearch:{get:function(){return this.lazySearch},set:function(e){this.lazySearch=e,this.$emit("update:search-input",e)}},isAnyValueAllowed:function(){return!1},isDirty:function(){return this.searchIsDirty||this.selectedItems.length>0},isSearching:function(){return this.multiple&&this.searchIsDirty||this.searchIsDirty&&this.internalSearch!==this.getText(this.selectedItem)},menuCanShow:function(){return!!this.isFocused&&(this.hasDisplayedItems||!this.hideNoData)},$_menuProps:function(){var e=a["a"].options.computed.$_menuProps.call(this);return e.contentClass="v-autocomplete__content ".concat(e.contentClass||"").trim(),Object(n["a"])(Object(n["a"])({},l),e)},searchIsDirty:function(){return null!=this.internalSearch&&""!==this.internalSearch},selectedItem:function(){var e=this;return this.multiple?null:this.selectedItems.find((function(t){return e.valueComparator(e.getValue(t),e.getValue(e.internalValue))}))},listData:function(){var e=a["a"].options.computed.listData.call(this);return e.props=Object(n["a"])(Object(n["a"])({},e.props),{},{items:this.virtualizedItems,noFilter:this.noFilter||!this.isSearching||!this.filteredItems.length,searchInput:this.internalSearch}),e}},watch:{filteredItems:"onFilteredItemsChanged",internalValue:"setSearch",isFocused:function(e){e?(document.addEventListener("copy",this.onCopy),this.$refs.input&&this.$refs.input.select()):(document.removeEventListener("copy",this.onCopy),this.$refs.input&&this.$refs.input.blur(),this.updateSelf())},isMenuActive:function(e){!e&&this.hasSlot&&(this.lazySearch=null)},items:function(e,t){t&&t.length||!this.hideNoData||!this.isFocused||this.isMenuActive||!e.length||this.activateMenu()},searchInput:function(e){this.lazySearch=e},internalSearch:"onInternalSearchChanged",itemText:"updateSelf"},created:function(){this.setSearch()},destroyed:function(){document.removeEventListener("copy",this.onCopy)},methods:{onFilteredItemsChanged:function(e,t){var i=this;e!==t&&(this.setMenuIndex(-1),this.$nextTick((function(){i.internalSearch&&(1===e.length||i.autoSelectFirst)&&(i.$refs.menu.getTiles(),i.setMenuIndex(0))})))},onInternalSearchChanged:function(){this.updateMenuDimensions()},updateMenuDimensions:function(){this.isMenuActive&&this.$refs.menu&&this.$refs.menu.updateDimensions()},changeSelectedIndex:function(e){this.searchIsDirty||(this.multiple&&e===o["y"].left?-1===this.selectedIndex?this.selectedIndex=this.selectedItems.length-1:this.selectedIndex--:this.multiple&&e===o["y"].right?this.selectedIndex>=this.selectedItems.length-1?this.selectedIndex=-1:this.selectedIndex++:e!==o["y"].backspace&&e!==o["y"].delete||this.deleteCurrentItem())},deleteCurrentItem:function(){var e=this.selectedIndex,t=this.selectedItems[e];if(this.isInteractive&&!this.getDisabled(t)){var i=this.selectedItems.length-1;if(-1!==this.selectedIndex||0===i){var n=this.selectedItems.length,a=e!==n-1?e:e-1,s=this.selectedItems[a];s?this.selectItem(t):this.setValue(this.multiple?[]:null),this.selectedIndex=a}else this.selectedIndex=i}},clearableCallback:function(){this.internalSearch=null,a["a"].options.methods.clearableCallback.call(this)},genInput:function(){var e=s["a"].options.methods.genInput.call(this);return e.data=Object(r["a"])(e.data,{attrs:{"aria-activedescendant":Object(o["p"])(this.$refs.menu,"activeTile.id"),autocomplete:Object(o["p"])(e.data,"attrs.autocomplete","off")},domProps:{value:this.internalSearch}}),e},genInputSlot:function(){var e=a["a"].options.methods.genInputSlot.call(this);return e.data.attrs.role="combobox",e},genSelections:function(){return this.hasSlot||this.multiple?a["a"].options.methods.genSelections.call(this):[]},onClick:function(e){this.isInteractive&&(this.selectedIndex>-1?this.selectedIndex=-1:this.onFocus(),this.isAppendInner(e.target)||this.activateMenu())},onInput:function(e){if(!(this.selectedIndex>-1)&&e.target){var t=e.target,i=t.value;t.value&&this.activateMenu(),this.internalSearch=i,this.badInput=t.validity&&t.validity.badInput}},onKeyDown:function(e){var t=e.keyCode;!e.ctrlKey&&[o["y"].home,o["y"].end].includes(t)||a["a"].options.methods.onKeyDown.call(this,e),this.changeSelectedIndex(t)},onSpaceDown:function(e){},onTabDown:function(e){a["a"].options.methods.onTabDown.call(this,e),this.updateSelf()},onUpDown:function(e){e.preventDefault(),this.activateMenu()},selectItem:function(e){a["a"].options.methods.selectItem.call(this,e),this.setSearch()},setSelectedItems:function(){a["a"].options.methods.setSelectedItems.call(this),this.isFocused||this.setSearch()},setSearch:function(){var e=this;this.$nextTick((function(){e.multiple&&e.internalSearch&&e.isMenuActive||(e.internalSearch=!e.selectedItems.length||e.multiple||e.hasSlot?null:e.getText(e.selectedItem))}))},updateSelf:function(){(this.searchIsDirty||this.internalValue)&&(this.valueComparator(this.internalSearch,this.getValue(this.internalValue))||this.setSearch())},hasItem:function(e){return this.selectedValues.indexOf(this.getValue(e))>-1},onCopy:function(e){var t,i;if(-1!==this.selectedIndex){var n=this.selectedItems[this.selectedIndex],a=this.getText(n);null==(t=e.clipboardData)||t.setData("text/plain",a),null==(i=e.clipboardData)||i.setData("text/vnd.vuetify.autocomplete.item+plain",a),e.preventDefault()}}}})},c964:function(e,t,i){"use strict";var n,a=i("3835"),s=(i("d81d"),i("caad"),i("2532"),i("a9e3"),i("1276"),i("ac1f"),i("99af"),i("466d"),i("498a"),i("7db0"),i("e635"),i("daf1")),r=i("50de"),o=i("58df");(function(e){e[e["Hour"]=1]="Hour",e[e["Minute"]=2]="Minute",e[e["Second"]=3]="Second"})(n||(n={}));var l=Object(o["a"])(s["a"]).extend({name:"v-time-picker-title",props:{ampm:Boolean,ampmReadonly:Boolean,disabled:Boolean,hour:Number,minute:Number,second:Number,period:{type:String,validator:function(e){return"am"===e||"pm"===e}},readonly:Boolean,useSeconds:Boolean,selecting:Number},methods:{genTime:function(){var e=this.hour;this.ampm&&(e=e?(e-1)%12+1:12);var t=null==this.hour?"--":this.ampm?String(e):Object(r["a"])(e),i=null==this.minute?"--":Object(r["a"])(this.minute),a=[this.genPickerButton("selecting",n.Hour,t,this.disabled),this.$createElement("span",":"),this.genPickerButton("selecting",n.Minute,i,this.disabled)];if(this.useSeconds){var s=null==this.second?"--":Object(r["a"])(this.second);a.push(this.$createElement("span",":")),a.push(this.genPickerButton("selecting",n.Second,s,this.disabled))}return this.$createElement("div",{class:"v-time-picker-title__time"},a)},genAmPm:function(){return this.$createElement("div",{staticClass:"v-time-picker-title__ampm",class:{"v-time-picker-title__ampm--readonly":this.ampmReadonly}},[this.ampmReadonly&&"am"!==this.period?null:this.genPickerButton("period","am",this.$vuetify.lang.t("$vuetify.timePicker.am"),this.disabled||this.readonly),this.ampmReadonly&&"pm"!==this.period?null:this.genPickerButton("period","pm",this.$vuetify.lang.t("$vuetify.timePicker.pm"),this.disabled||this.readonly)])}},render:function(e){var t=[this.genTime()];return this.ampm&&t.push(this.genAmPm()),e("div",{staticClass:"v-time-picker-title"},t)}}),u=i("5530"),c=(i("2af1"),i("1c58"),i("a9ad")),h=i("7560"),d=Object(o["a"])(c["a"],h["a"]).extend({name:"v-time-picker-clock",props:{allowedValues:Function,ampm:Boolean,disabled:Boolean,double:Boolean,format:{type:Function,default:function(e){return e}},max:{type:Number,required:!0},min:{type:Number,required:!0},scrollable:Boolean,readonly:Boolean,rotate:{type:Number,default:0},step:{type:Number,default:1},value:Number},data:function(){return{inputValue:this.value,isDragging:!1,valueOnMouseDown:null,valueOnMouseUp:null}},computed:{count:function(){return this.max-this.min+1},degreesPerUnit:function(){return 360/this.roundCount},degrees:function(){return this.degreesPerUnit*Math.PI/180},displayedValue:function(){return null==this.value?this.min:this.value},innerRadiusScale:function(){return.62},roundCount:function(){return this.double?this.count/2:this.count}},watch:{value:function(e){this.inputValue=e}},methods:{wheel:function(e){e.preventDefault();var t=Math.sign(-e.deltaY||1),i=this.displayedValue;do{i+=t,i=(i-this.min+this.count)%this.count+this.min}while(!this.isAllowed(i)&&i!==this.displayedValue);i!==this.displayedValue&&this.update(i)},isInner:function(e){return this.double&&e-this.min>=this.roundCount},handScale:function(e){return this.isInner(e)?this.innerRadiusScale:1},isAllowed:function(e){return!this.allowedValues||this.allowedValues(e)},genValues:function(){for(var e=[],t=this.min;t<=this.max;t+=this.step){var i=t===this.value&&(this.color||"accent");e.push(this.$createElement("span",this.setBackgroundColor(i,{staticClass:"v-time-picker-clock__item",class:{"v-time-picker-clock__item--active":t===this.displayedValue,"v-time-picker-clock__item--disabled":this.disabled||!this.isAllowed(t)},style:this.getTransform(t),domProps:{innerHTML:"<span>".concat(this.format(t),"</span>")}})))}return e},genHand:function(){var e="scaleY(".concat(this.handScale(this.displayedValue),")"),t=this.rotate+this.degreesPerUnit*(this.displayedValue-this.min),i=null!=this.value&&(this.color||"accent");return this.$createElement("div",this.setBackgroundColor(i,{staticClass:"v-time-picker-clock__hand",class:{"v-time-picker-clock__hand--inner":this.isInner(this.value)},style:{transform:"rotate(".concat(t,"deg) ").concat(e)}}))},getTransform:function(e){var t=this.getPosition(e),i=t.x,n=t.y;return{left:"".concat(50+50*i,"%"),top:"".concat(50+50*n,"%")}},getPosition:function(e){var t=this.rotate*Math.PI/180;return{x:Math.sin((e-this.min)*this.degrees+t)*this.handScale(e),y:-Math.cos((e-this.min)*this.degrees+t)*this.handScale(e)}},onMouseDown:function(e){e.preventDefault(),this.valueOnMouseDown=null,this.valueOnMouseUp=null,this.isDragging=!0,this.onDragMove(e)},onMouseUp:function(e){e.stopPropagation(),this.isDragging=!1,null!==this.valueOnMouseUp&&this.isAllowed(this.valueOnMouseUp)&&this.$emit("change",this.valueOnMouseUp)},onDragMove:function(e){if(e.preventDefault(),this.isDragging||"click"===e.type)for(var t,i=this.$refs.clock.getBoundingClientRect(),n=i.width,a=i.top,s=i.left,r=this.$refs.innerClock.getBoundingClientRect(),o=r.width,l=("touches"in e?e.touches[0]:e),u=l.clientX,c=l.clientY,h={x:n/2,y:-n/2},d={x:u-s,y:a-c},m=Math.round(this.angle(h,d)-this.rotate+360)%360,p=this.double&&this.euclidean(h,d)<(o+o*this.innerRadiusScale)/4,f=Math.ceil(15/this.degreesPerUnit),v=0;v<f;v++){if(t=this.angleToValue(m+v*this.degreesPerUnit,p),this.isAllowed(t))return this.setMouseDownValue(t);if(t=this.angleToValue(m-v*this.degreesPerUnit,p),this.isAllowed(t))return this.setMouseDownValue(t)}},angleToValue:function(e,t){var i=(Math.round(e/this.degreesPerUnit)+(t?this.roundCount:0))%this.count+this.min;return e<360-this.degreesPerUnit/2?i:t?this.max-this.roundCount+1:this.min},setMouseDownValue:function(e){null===this.valueOnMouseDown&&(this.valueOnMouseDown=e),this.valueOnMouseUp=e,this.update(e)},update:function(e){this.inputValue!==e&&(this.inputValue=e,this.$emit("input",e))},euclidean:function(e,t){var i=t.x-e.x,n=t.y-e.y;return Math.sqrt(i*i+n*n)},angle:function(e,t){var i=2*Math.atan2(t.y-e.y-this.euclidean(e,t),t.x-e.x);return Math.abs(180*i/Math.PI)}},render:function(e){var t=this,i={staticClass:"v-time-picker-clock",class:Object(u["a"])({"v-time-picker-clock--indeterminate":null==this.value},this.themeClasses),on:this.readonly||this.disabled?void 0:{mousedown:this.onMouseDown,mouseup:this.onMouseUp,mouseleave:function(e){return t.isDragging&&t.onMouseUp(e)},touchstart:this.onMouseDown,touchend:this.onMouseUp,mousemove:this.onDragMove,touchmove:this.onDragMove},ref:"clock"};return this.scrollable&&i.on&&(i.on.wheel=this.wheel),e("div",i,[e("div",{staticClass:"v-time-picker-clock__inner",ref:"innerClock"},[this.genHand(),this.genValues()])])}}),m=i("4754"),p=i("80d2"),f=Object(p["h"])(24),v=Object(p["h"])(12),g=v.map((function(e){return e+12})),b=Object(p["h"])(60),k={1:"hour",2:"minute",3:"second"};t["a"]=Object(o["a"])(m["a"],s["a"]).extend({name:"v-time-picker",props:{allowedHours:[Function,Array],allowedMinutes:[Function,Array],allowedSeconds:[Function,Array],disabled:Boolean,format:{type:String,default:"ampm",validator:function(e){return["ampm","24hr"].includes(e)}},min:String,max:String,readonly:Boolean,scrollable:Boolean,useSeconds:Boolean,value:null,ampmInTitle:Boolean},data:function(){return{inputHour:null,inputMinute:null,inputSecond:null,lazyInputHour:null,lazyInputMinute:null,lazyInputSecond:null,period:"am",selecting:n.Hour}},computed:{selectingHour:{get:function(){return this.selecting===n.Hour},set:function(e){this.selecting=n.Hour}},selectingMinute:{get:function(){return this.selecting===n.Minute},set:function(e){this.selecting=n.Minute}},selectingSecond:{get:function(){return this.selecting===n.Second},set:function(e){this.selecting=n.Second}},isAllowedHourCb:function(){var e,t=this;if(e=this.allowedHours instanceof Array?function(e){return t.allowedHours.includes(e)}:this.allowedHours,!this.min&&!this.max)return e;var i=this.min?Number(this.min.split(":")[0]):0,n=this.max?Number(this.max.split(":")[0]):23;return function(t){return t>=1*i&&t<=1*n&&(!e||e(t))}},isAllowedMinuteCb:function(){var e,t=this,i=!this.isAllowedHourCb||null===this.inputHour||this.isAllowedHourCb(this.inputHour);if(e=this.allowedMinutes instanceof Array?function(e){return t.allowedMinutes.includes(e)}:this.allowedMinutes,!this.min&&!this.max)return i?e:function(){return!1};var n=this.min?this.min.split(":").map(Number):[0,0],s=Object(a["a"])(n,2),r=s[0],o=s[1],l=this.max?this.max.split(":").map(Number):[23,59],u=Object(a["a"])(l,2),c=u[0],h=u[1],d=60*r+1*o,m=60*c+1*h;return function(n){var a=60*t.inputHour+n;return a>=d&&a<=m&&i&&(!e||e(n))}},isAllowedSecondCb:function(){var e,t=this,i=!this.isAllowedHourCb||null===this.inputHour||this.isAllowedHourCb(this.inputHour),n=i&&(!this.isAllowedMinuteCb||null===this.inputMinute||this.isAllowedMinuteCb(this.inputMinute));if(e=this.allowedSeconds instanceof Array?function(e){return t.allowedSeconds.includes(e)}:this.allowedSeconds,!this.min&&!this.max)return n?e:function(){return!1};var s=this.min?this.min.split(":").map(Number):[0,0,0],r=Object(a["a"])(s,3),o=r[0],l=r[1],u=r[2],c=this.max?this.max.split(":").map(Number):[23,59,59],h=Object(a["a"])(c,3),d=h[0],m=h[1],p=h[2],f=3600*o+60*l+1*(u||0),v=3600*d+60*m+1*(p||0);return function(i){var a=3600*t.inputHour+60*t.inputMinute+i;return a>=f&&a<=v&&n&&(!e||e(i))}},isAmPm:function(){return"ampm"===this.format}},watch:{value:"setInputData"},mounted:function(){this.setInputData(this.value),this.$on("update:period",this.setPeriod)},methods:{genValue:function(){return null==this.inputHour||null==this.inputMinute||this.useSeconds&&null==this.inputSecond?null:"".concat(Object(r["a"])(this.inputHour),":").concat(Object(r["a"])(this.inputMinute))+(this.useSeconds?":".concat(Object(r["a"])(this.inputSecond)):"")},emitValue:function(){var e=this.genValue();null!==e&&this.$emit("input",e)},setPeriod:function(e){if(this.period=e,null!=this.inputHour){var t=this.inputHour+("am"===e?-12:12);this.inputHour=this.firstAllowed("hour",t),this.emitValue()}},setInputData:function(e){if(null==e||""===e)this.inputHour=null,this.inputMinute=null,this.inputSecond=null;else if(e instanceof Date)this.inputHour=e.getHours(),this.inputMinute=e.getMinutes(),this.inputSecond=e.getSeconds();else{var t=e.trim().toLowerCase().match(/^(\d+):(\d+)(:(\d+))?([ap]m)?$/)||new Array(6),i=Object(a["a"])(t,6),n=i[1],s=i[2],r=i[4],o=i[5];this.inputHour=o?this.convert12to24(parseInt(n,10),o):parseInt(n,10),this.inputMinute=parseInt(s,10),this.inputSecond=parseInt(r||0,10)}this.period=null==this.inputHour||this.inputHour<12?"am":"pm"},convert24to12:function(e){return e?(e-1)%12+1:12},convert12to24:function(e,t){return e%12+("pm"===t?12:0)},onInput:function(e){this.selecting===n.Hour?this.inputHour=this.isAmPm?this.convert12to24(e,this.period):e:this.selecting===n.Minute?this.inputMinute=e:this.inputSecond=e,this.emitValue()},onChange:function(e){this.$emit("click:".concat(k[this.selecting]),e);var t=this.selecting===(this.useSeconds?n.Second:n.Minute);if(this.selecting===n.Hour?this.selecting=n.Minute:this.useSeconds&&this.selecting===n.Minute&&(this.selecting=n.Second),this.inputHour!==this.lazyInputHour||this.inputMinute!==this.lazyInputMinute||this.useSeconds&&this.inputSecond!==this.lazyInputSecond){var i=this.genValue();null!==i&&(this.lazyInputHour=this.inputHour,this.lazyInputMinute=this.inputMinute,this.useSeconds&&(this.lazyInputSecond=this.inputSecond),t&&this.$emit("change",i))}},firstAllowed:function(e,t){var i="hour"===e?this.isAllowedHourCb:"minute"===e?this.isAllowedMinuteCb:this.isAllowedSecondCb;if(!i)return t;var n="minute"===e||"second"===e?b:this.isAmPm?t<12?v:g:f,a=n.find((function(e){return i((e+t)%n.length+n[0])}));return((a||0)+t)%n.length+n[0]},genClock:function(){return this.$createElement(d,{props:{allowedValues:this.selecting===n.Hour?this.isAllowedHourCb:this.selecting===n.Minute?this.isAllowedMinuteCb:this.isAllowedSecondCb,color:this.color,dark:this.dark,disabled:this.disabled,double:this.selecting===n.Hour&&!this.isAmPm,format:this.selecting===n.Hour?this.isAmPm?this.convert24to12:function(e){return e}:function(e){return Object(r["a"])(e,2)},light:this.light,max:this.selecting===n.Hour?this.isAmPm&&"am"===this.period?11:23:59,min:this.selecting===n.Hour&&this.isAmPm&&"pm"===this.period?12:0,readonly:this.readonly,scrollable:this.scrollable,size:Number(this.width)-(!this.fullWidth&&this.landscape?80:20),step:this.selecting===n.Hour?1:5,value:this.selecting===n.Hour?this.inputHour:this.selecting===n.Minute?this.inputMinute:this.inputSecond},on:{input:this.onInput,change:this.onChange},ref:"clock"})},genClockAmPm:function(){return this.$createElement("div",this.setTextColor(this.color||"primary",{staticClass:"v-time-picker-clock__ampm"}),[this.genPickerButton("period","am",this.$vuetify.lang.t("$vuetify.timePicker.am"),this.disabled||this.readonly),this.genPickerButton("period","pm",this.$vuetify.lang.t("$vuetify.timePicker.pm"),this.disabled||this.readonly)])},genPickerBody:function(){return this.$createElement("div",{staticClass:"v-time-picker-clock__container",key:this.selecting},[!this.ampmInTitle&&this.isAmPm&&this.genClockAmPm(),this.genClock()])},genPickerTitle:function(){var e=this;return this.$createElement(l,{props:{ampm:this.isAmPm,ampmReadonly:this.isAmPm&&!this.ampmInTitle,disabled:this.disabled,hour:this.inputHour,minute:this.inputMinute,second:this.inputSecond,period:this.period,readonly:this.readonly,useSeconds:this.useSeconds,selecting:this.selecting},on:{"update:selecting":function(t){return e.selecting=t},"update:period":function(t){return e.$emit("update:period",t)}},ref:"title",slot:"title"})}},render:function(){return this.genPicker("v-picker--time")}})},e635:function(e,t,i){}}]);
//# sourceMappingURL=chunk-529ddd7c.b46d028e.js.map