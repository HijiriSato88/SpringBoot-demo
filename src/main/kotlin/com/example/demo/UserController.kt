package com.example.demo

import com.example.demo.database.*
import org.springframework.web.bind.annotation.*

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@RestController
@RequestMapping("user")
class UserController(
    val userMapper: UserMapper
) {

    @GetMapping("/select/{id}")
    fun select(@PathVariable("id") id: Int): UserRecord? {
        return userMapper.selectByPrimaryKey(id)
    }

    @PostMapping("/register")
    fun insert(@RequestBody request: InsertRequest): InsertResponse {
        val record = UserRecord(request.id, request.name, request.age, request.profile)
        return InsertResponse(userMapper.insert(record))
    }

    @PutMapping("/update")
    fun update(@RequestBody request: InsertRequest) {
        val record = UserRecord(request.id, request.name, request.age, request.profile)
        userMapper.updateByPrimaryKeySelective(record)
    }

    @DeleteMapping("delete/{user_id}")
    fun delete(@PathVariable("user_id") userId: Int){
        userMapper.deleteByPrimaryKey(userId)
    }

}

// リクエスト
data class InsertRequest(
    val id: Int,
    val name: String,
    val age: Int,
    val profile: String
)

// レスポンス
data class InsertResponse(
    val count: Int
)