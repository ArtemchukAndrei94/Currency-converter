<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>


    <div class="container" xmlns="http://www.w3.org/1999/html">
        <div class="row">
            <div class="col">
                <@l.logout />
            </div>
            <div class="col">
                <form action="history" method="get">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-primary">История Конвертаций</button>
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
        <div class="row">
            <form action="/main" method="post">
                <div class="jumbotron">
                    <h2 class="display-4">Конвертер</h2>

                    <div class="container">
                        <div class="row align-items-start">
                            <div class="col">
                                <select class="form-control" name="charcode1">
                                    <#list currencies as currency>
                                        <option value="${currency.charcode}">${currency.charcode} (${currency.name})
                                        </option>
                                    </#list>
                                </select>
                            </div>
                            <div class="col">
                                <select class="form-control" name="charcode2">
                                    <#list currencies as currency>
                                        <option value="${currency.charcode}">${currency.charcode} (${currency.name})
                                        </option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                        <hr class="my-4">
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label>Сумма которую хотите сконвертировать</label>
                                    <input type="number" class="form-control" name="count" value="${startValue}">
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label>Результат</label>
                                    <input type="number" class="form-control"
                                           readonly="readonly" value="${result}">
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    </div>
                    <button type="submit" class="btn btn-primary">Конвертировать</button>
                </div>
            </form>
        </div>
    </div>




</@c.page>