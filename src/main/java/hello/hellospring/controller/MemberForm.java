package hello.hellospring.controller;

public class MemberForm {
    private String name; // createMemberForm.html에서 input tag의 name과 matching

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}