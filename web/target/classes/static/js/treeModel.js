function treeModel(funcOnSelectItem) {
    var model = this;

    var svg = {};
    var tree = {};
    var diagonal = {};

    function createSvg() {
        var margin = {top: 20, right: 120, bottom: 20, left: 120};
        var width = 1200 - margin.right - margin.left;
        var height = 500 - margin.top - margin.bottom;

        tree = d3.layout.tree()
            .size([height, width]);

        diagonal = d3.svg.diagonal()
            .projection(function (d) {
                return [d.y, d.x];
            });


        $(".tree_section").empty();

        svg = d3.select(".tree_section").append("svg")
            .attr("width", width + margin.right + margin.left)
            .attr("height", height + margin.top + margin.bottom)
            .append("g")
            .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    }


    model.setData = function (root) {

        createSvg();



        var i = 0;
        // Compute the new tree layout.
        var nodes = tree.nodes(root).reverse(),
            links = tree.links(nodes);

        // Normalize for fixed-depth.
        nodes.forEach(function (d) {
            d.y = d.depth * 180;
        });

        // Declare the nodes…
        var node = svg.selectAll("g.node")
            .data(nodes, function (d) {
                return d.id || (d.id = ++i);
            });

        // Enter the nodes.
        var nodeEnter = node.enter().append("g")
            .attr("class", "node")
            .attr("transform", function (d) {
                return "translate(" + d.y + "," + d.x + ")";
            });

        nodeEnter.append("circle")
            .attr("r", 10)
            .style("fill", "#fff").on("click", function (d) {
            funcOnSelectItem(d);
        });

        nodeEnter.append("text")
            .attr("x", function (d) {
                return d.children || d._children ? -13 : 13;
            })
            .attr("dy", ".35em")
            .attr("text-anchor", function (d) {
                return d.children || d._children ? "end" : "start";
            })
            .text(function (d) {
                return d.name;
            })
            .style("fill-opacity", 1);

        // Declare the links…
        var link = svg.selectAll("path.link")
            .data(links, function (d) {
                return d.target.id;
            });

        // Enter the links.
        link.enter().insert("path", "g")
            .attr("class", "link")
            .attr("d", diagonal).style("stroke-width", 2);
    }


}
