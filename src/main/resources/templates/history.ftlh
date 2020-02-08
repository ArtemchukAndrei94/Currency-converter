<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>

    <div class="container" xmlns="http://www.w3.org/1999/html">
        <div class="row">
            <div class="col">
                <@l.logout />
            </div>
            <div class="col">
                <form action="/main" method="get">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-primary">Конвертер</button>
                </form>
            </div>
            <div class="col">
            </div>
            <div class="col">
            </div>
            <div class="col">
            </div>
        </div>
    </div>
    <br/>



    <div class="container">
        <div class="row align-items-start">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th scope="col">Исходная валюта</th>
                    <th scope="col">Целевая валюта</th>
                    <th scope="col">Исходная сумма</th>
                    <th scope="col">Получаемая сумма</th>
                    <th scope="col">Дата</th>
                </tr>
                </thead>
                <tbody>
                <#list history as h>
                    <tr>
                        <td>${h.first_currency_name}</td>
                        <td>${h.second_currency_name}</td>
                        <td>${h.original_amount}</td>
                        <td>${h.result_amount}</td>
                        <td>${h.date_conversion}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>

</@c.page>