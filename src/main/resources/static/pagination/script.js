/*
 * jQuery Bootstrap Pagination v1.3.1
 * https://github.com/esimakin/twbs-pagination
 *
 * Copyright 2014-2015 Eugene Simakin <eugenesimakin@mail.ru>
 * Released under Apache 2.0 license
 * http://apache.org/licenses/LICENSE-2.0.html
 */
!(function (a, b, c, d) {
    "use strict";
    var e = a.fn.twbsPagination,
        f = function (c, d) {
            if (
                ((this.$element = a(c)),
                    (this.options = a.extend({}, a.fn.twbsPagination.defaults, d)),
                this.options.startPage < 1 ||
                this.options.startPage > this.options.totalPages)
            )
                throw new Error("Start page option is incorrect");
            if (
                ((this.options.totalPages = parseInt(this.options.totalPages)),
                    isNaN(this.options.totalPages))
            )
                throw new Error("Total pages option is not correct!");
            if (
                ((this.options.visiblePages = parseInt(this.options.visiblePages)),
                    isNaN(this.options.visiblePages))
            )
                throw new Error("Visible pages option is not correct!");
            if (
                (this.options.totalPages < this.options.visiblePages &&
                (this.options.visiblePages = this.options.totalPages),
                this.options.onPageClick instanceof Function &&
                this.$element.first().on("page", this.options.onPageClick),
                    this.options.href)
            ) {
                var e,
                    f = this.options.href.replace(/[-\/\\^$*+?.|[\]]/g, "\\$&");
                (f = f.replace(this.options.hrefVariable, "(\\d+)")),
                null != (e = new RegExp(f, "i").exec(b.location.href)) &&
                (this.options.startPage = parseInt(e[1], 10));
            }
            var g =
                "function" == typeof this.$element.prop
                    ? this.$element.prop("tagName")
                    : this.$element.attr("tagName");
            return (
                "UL" === g
                    ? (this.$listContainer = this.$element)
                    : (this.$listContainer = a("<ul></ul>")),
                    this.$listContainer.addClass(this.options.paginationClass),
                "UL" !== g && this.$element.append(this.$listContainer),
                    this.render(this.getPages(this.options.startPage)),
                    this.setupEvents(),
                this.options.initiateStartPageClick &&
                this.$element.trigger("page", this.options.startPage),
                    this
            );
        };
    (f.prototype = {
        constructor: f,
        destroy: function () {
            return (
                this.$element.empty(),
                    this.$element.removeData("twbs-pagination"),
                    this.$element.off("page"),
                    this
            );
        },
        show: function (a) {
            if (1 > a || a > this.options.totalPages)
                throw new Error("Page is incorrect.");
            return (
                this.render(this.getPages(a)),
                    this.setupEvents(),
                    this.$element.trigger("page", a),
                    this
            );
        },
        buildListItems: function (a) {
            var b = [];
            if (
                (this.options.first && b.push(this.buildItem("first", 1)),
                    this.options.prev)
            ) {
                var c =
                    a.currentPage > 1
                        ? a.currentPage - 1
                        : this.options.loop
                            ? this.options.totalPages
                            : 1;
                b.push(this.buildItem("prev", c));
            }
            for (var d = 0; d < a.numeric.length; d++)
                b.push(this.buildItem("page", a.numeric[d]));
            if (this.options.next) {
                var e =
                    a.currentPage < this.options.totalPages
                        ? a.currentPage + 1
                        : this.options.loop
                            ? 1
                            : this.options.totalPages;
                b.push(this.buildItem("next", e));
            }
            return (
                this.options.last &&
                b.push(this.buildItem("last", this.options.totalPages)),
                    b
            );
        },
        buildItem: function (b, c) {
            var d = a("<li></li>"),
                e = a("<a></a>"),
                f = null;
            switch (b) {
                case "page":
                    (f = c), d.addClass(this.options.pageClass);
                    break;
                case "first":
                    (f = this.options.first), d.addClass(this.options.firstClass);
                    break;
                case "prev":
                    (f = this.options.prev), d.addClass(this.options.prevClass);
                    break;
                case "next":
                    (f = this.options.next), d.addClass(this.options.nextClass);
                    break;
                case "last":
                    (f = this.options.last), d.addClass(this.options.lastClass);
            }
            return (
                d.data("page", c),
                    d.data("page-type", b),
                    d.append(e.attr("href", this.makeHref(c)).html(f)),
                    d
            );
        },
        getPages: function (a) {
            var b = [],
                c = Math.floor(this.options.visiblePages / 2),
                d = a - c + 1 - (this.options.visiblePages % 2),
                e = a + c;
            0 >= d && ((d = 1), (e = this.options.visiblePages)),
            e > this.options.totalPages &&
            ((d = this.options.totalPages - this.options.visiblePages + 1),
                (e = this.options.totalPages));
            for (var f = d; e >= f; ) b.push(f), f++;
            return { currentPage: a, numeric: b };
        },
        render: function (b) {
            var c = this;
            this.$listContainer.children().remove(),
                this.$listContainer.append(this.buildListItems(b)),
                this.$listContainer.children().each(function () {
                    var d = a(this),
                        e = d.data("page-type");
                    switch (e) {
                        case "page":
                            d.data("page") === b.currentPage &&
                            d.addClass(c.options.activeClass);
                            break;
                        case "first":
                            d.toggleClass(c.options.disabledClass, 1 === b.currentPage);
                            break;
                        case "last":
                            d.toggleClass(
                                c.options.disabledClass,
                                b.currentPage === c.options.totalPages
                            );
                            break;
                        case "prev":
                            d.toggleClass(
                                c.options.disabledClass,
                                !c.options.loop && 1 === b.currentPage
                            );
                            break;
                        case "next":
                            d.toggleClass(
                                c.options.disabledClass,
                                !c.options.loop && b.currentPage === c.options.totalPages
                            );
                    }
                });
        },
        setupEvents: function () {
            var b = this;
            this.$listContainer.find("li").each(function () {
                var c = a(this);
                return (
                    c.off(),
                        c.hasClass(b.options.disabledClass) ||
                        c.hasClass(b.options.activeClass)
                            ? void c.on("click", !1)
                            : void c.click(function (a) {
                                !b.options.href && a.preventDefault(),
                                    b.show(parseInt(c.data("page")));
                            })
                );
            });
        },
        makeHref: function (a) {
            return this.options.href
                ? this.options.href.replace(this.options.hrefVariable, a)
                : "#";
        }
    }),
        (a.fn.twbsPagination = function (b) {
            var c,
                e = Array.prototype.slice.call(arguments, 1),
                g = a(this),
                h = g.data("twbs-pagination"),
                i = "object" == typeof b && b;
            return (
                h || g.data("twbs-pagination", (h = new f(this, i))),
                "string" == typeof b && (c = h[b].apply(h, e)),
                    c === d ? g : c
            );
        }),
        (a.fn.twbsPagination.defaults = {
            totalPages: 0,
            startPage: 1,
            visiblePages: 5,
            initiateStartPageClick: !0,
            href: !1,
            hrefVariable: "{{number}}",
            first: "First",
            prev: "Previous",
            next: "Next",
            last: "Last",
            loop: !1,
            onPageClick: null,
            paginationClass: "pagination",
            nextClass: "next",
            prevClass: "prev",
            lastClass: "last",
            firstClass: "first",
            pageClass: "page",
            activeClass: "active",
            disabledClass: "disabled"
        }),
        (a.fn.twbsPagination.Constructor = f),
        (a.fn.twbsPagination.noConflict = function () {
            return (a.fn.twbsPagination = e), this;
        });
})(window.jQuery, window, document);