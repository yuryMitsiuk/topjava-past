var ajaxUrl = "ajax/profile/meals/";
var datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": true,
        "info": true,
        "columns": [
            {
                "data": "dateTime"
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ]
    });
    makeEditable();
});

function edit(id) {
    $.ajax({
        url: ajaxUrl + id,
        dataType: "json",
        success: function (data) {
            $.each(data, function (i, val) {
                var idn = "#"+i;
                $("#detailsForm").find(idn).val(val);
            })
            $("#editRow").modal();
        }
    })
}

function filter() {
    var form = $("#filterform");
    $.ajax({
        type: "POST",
        url: ajaxUrl+"filter",
        data: form.serialize(),
        success: function (data) {
            datatableApi.clear().rows.add(data).draw();
            successNoty("Filtered");
        }
    })
}

function clearFilter() {
    $("#filterform").find(":input").val("");
    updateTable();
}
